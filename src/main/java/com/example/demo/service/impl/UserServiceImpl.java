package com.example.demo.service.impl;

import ch.qos.logback.core.joran.util.beans.BeanUtil;
import com.alibaba.excel.util.StringUtils;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.demo.common.constants.ExceptionEnumConstant;
import com.example.demo.common.utils.CaptchaUtil;
import com.example.demo.common.utils.EncryptUtil;
import com.example.demo.config.LoginCaptchaProperties;
import com.example.demo.exception.ServiceException;
import com.example.demo.mapper.UserMapper;
import com.example.demo.model.dto.capcha.LoginCaptchaDto;
import com.example.demo.model.entity.BaseModel;
import com.example.demo.model.entity.UserDO;
import com.example.demo.model.vo.PageVo;
import com.example.demo.service.UserService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * <p>
 * 服务实现类
 * </p>
 *
 * @author generator@研发组
 * @since 2022-10-24
 */
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, UserDO> implements UserService {

    @Autowired
    private LoginCaptchaProperties captchaConfig;

    @Override
    public PageInfo<UserDO> getlist(UserDO user, String keyword, PageVo pageVo) {
        PageHelper.startPage(pageVo.getPageParam());
        List<UserDO> userList = this.baseMapper.getList(user, keyword);
        return new PageInfo<>(userList);
    }

    @Override
    public void addUser(UserDO user) {
        UserDO userDO = new UserDO();
        BeanUtils.copyProperties(user, userDO);
        userDO.setCreateTime(new Date());
        //判断用户名是否存在
        if (this.list(new QueryWrapper<UserDO>().lambda().eq(UserDO::getUsername, user.getUsername()).eq(UserDO::getIsDelete, false)).size() != 0) {
            throw new ServiceException(ExceptionEnumConstant.USER_EXIT_ERROR);
        }
        this.save(userDO);
    }

    @Override
    public void updateUser(UserDO user) {
        if (user.getPassword().length()<=4) {
            throw new ServiceException(ExceptionEnumConstant.USER_PASSWORD_ILLEGAL);
        }
        UserDO userDO = new UserDO();
        BeanUtils.copyProperties(user, userDO);
        userDO.setPassword(EncryptUtil.encrypt(user.getPassword())).setUpdateTime(new Date());
        //判断除当前该用户id外是否还有同名用户
        if (this.list(new QueryWrapper<UserDO>().lambda().eq(UserDO::getUsername, user.getUsername()).eq(UserDO::getIsDelete, false).ne(BaseModel::getId, userDO.getId())).size() != 0) {
            throw new ServiceException(ExceptionEnumConstant.USER_EXIT_ERROR);
        }
        this.updateById(userDO);
    }

    @Override
    public void delUser(Set<Long> ids) {
        this.update(null, new UpdateWrapper<UserDO>().lambda().in(BaseModel::getId, ids).set(UserDO::getIsDelete, true));
    }

    @Override
    public boolean verifyCaptcha(String captcha, String tag) {
        try {
            LoginCaptchaDto captchaBO = CaptchaUtil.decodeTag(captchaConfig.getSecret(), captchaConfig.getIv(), tag);
            return captcha.equalsIgnoreCase(captchaBO.getCaptcha()) || System.currentTimeMillis() > captchaBO.getExpired();
        } catch (Exception e) {
            log.error(e.getMessage(), e);
            return false;
        }
    }

    @Override
    public UserDO login(UserDO user) {
        UserDO userDO = this.getUserByUsername(user.getUsername());
        if (userDO == null) {
            throw new ServiceException(ExceptionEnumConstant.USER_NOT_EXIT_ERROR);
        }
        if (!EncryptUtil.pattern.matcher(user.getPassword()).find() ||
                (user.getPassword().length() == 8 && (user.getPassword().contains("123456") || user.getPassword().contains("234567")))) {
            throw new ServiceException(ExceptionEnumConstant.USER_FORBID_LOGIN);
        }
        return userDO;
    }

    @Transactional
    @Override
    public void register(UserDO userDO) {
        if (StringUtils.isBlank(userDO.getUsername()) || StringUtils.isBlank(userDO.getPassword())) {
            throw new ServiceException(ExceptionEnumConstant.REQUEST_PARAM_IS_NULL);
        }
        if (userDO.getPassword().length()<=4) {
            throw new ServiceException(ExceptionEnumConstant.USER_PASSWORD_ILLEGAL);
        }
        if (!EncryptUtil.pattern.matcher(userDO.getPassword()).find() ||
                (userDO.getPassword().length() == 8 && (userDO.getPassword().contains("123456") || userDO.getPassword().contains("234567")))) {
            throw new ServiceException(ExceptionEnumConstant.USER_FORBID_LOGIN);
        }
        if (checkUserExistByUsername(userDO.getUsername())) {
            throw new ServiceException(ExceptionEnumConstant.USER_EXIT_ERROR);
        }
        if (StringUtils.isNotBlank(userDO.getEmail())) {
            if (checkUserExistByEmail(userDO.getEmail())) {
                throw new ServiceException(ExceptionEnumConstant.EMAIL_USED_ERROR);
            }
        } else {
            // 前端如果传入的email为 "" 时，由于数据库中存在""的email，会报duplication错误
            // 所以如果email为blank，必须显示设置为 null
            userDO.setEmail(null);
        }
        UserDO user = new UserDO();
        BeanUtils.copyProperties(userDO, user);
        // 密码加密
        user.setPassword(EncryptUtil.encrypt(userDO.getPassword())).setUpdateTime(new Date());
        this.baseMapper.insert(user);

//        if (dto.getGroupIds() != null && !dto.getGroupIds().isEmpty()) {
//            checkGroupsValid(dto.getGroupIds());
//            checkGroupsExist(dto.getGroupIds());
//            List<UserGroupDO> relations = dto.getGroupIds().stream()
//                    .map(groupId -> new UserGroupDO(user.getId(), groupId))
//                    .collect(Collectors.toList());
//            userGroupMapper.insertBatch(relations);
//        } else {
//            //游客分组
//            Long guestGroupId = groupService.getParticularGroupIdByLevel(GroupLevelEnum.GUEST);
//            UserGroupDO relation = new UserGroupDO(user.getId(), guestGroupId);
//            userGroupMapper.insert(relation);
//        }
    }


    @Override
    public UserDO getUserByUsername(String username) {
        QueryWrapper<UserDO> wrapper = new QueryWrapper<>();
        wrapper.lambda().eq(UserDO::getUsername, username);
        return this.getOne(wrapper);
    }

    @Override
    public boolean checkUserExistByUsername(String username) {
        int rows = this.baseMapper.selectCountByUsername(username);
        return rows > 0;
    }

    @Override
    public boolean checkUserExistByEmail(String email) {
        QueryWrapper<UserDO> wrapper = new QueryWrapper<>();
        wrapper.lambda().eq(UserDO::getEmail, email);
        int rows = this.baseMapper.selectCount(wrapper);
        return rows > 0;
    }

    @Override
    public boolean checkUserExistById(Long id) {
        int rows = this.baseMapper.selectCountById(id);
        return rows > 0;
    }
//
//    @Override
//    public Token login(LoginVo loginVo) {
//        UserDO user = this.getUserByUsername(loginVo.getUsername());
//        if (user == null) {
//            throw new ServiceException(ExceptionEnumConstant.USER_NOT_EXIT_ERROR);
//        }
//        try {
//            loginVo.setPassword(CaptchaUtil.aesDecode(captchaConfig.getSecret(), loginVo.getPassword()));
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//        String pwd = loginVo.getPassword();
//        if (!EncryptUtil.pattern.matcher(pwd).find() ||
//                (pwd.length() == 8 && (pwd.contains("123456") || pwd.contains("234567")))) {
//            throw new ServiceException(ExceptionEnumConstant.USER_FORBID_LOGIN);
//        }
//        //校验密码
//        boolean verify = EncryptUtil.verify(user.getPassword(), loginVo.getPassword());
//        if (!verify) {
//            throw new ServiceException(ExceptionEnumConstant.USER_NAME_PASSWORD_ERROR);
//        }
//        //检查是否是管理员角色
//        List<UserGroupDO> userGroupList = userGroupMapper.selectList(new QueryWrapper<UserGroupDO>()
//                .lambda().eq(UserGroupDO::getUserId, user.getId()));
//        Boolean isAdmin = false;
//        String groupLevel = null;
//
//        //组id现在规定 一个用户只对应一个
//        Set<Long> groupIds = new LinkedHashSet<>();
//        if (!CollectionUtils.isEmpty(userGroupList)) {
//            groupIds = userGroupList.stream()
//                    .map(userGroupDO -> userGroupDO.getGroupId())
//                    .collect(Collectors.toSet());
//
//            List<GroupDO> groupList = groupService.list(new QueryWrapper<GroupDO>()
//                    .lambda().in(GroupDO::getId, groupIds));
//
//            isAdmin = groupList.stream().anyMatch(groupDO ->
//                    groupDO.getLevel() != null && groupDO.getLevel().equals(GroupLevelEnum.ROOT.getValue()));
//
//            for (GroupDO groupDO : groupList) {
//                groupLevel = groupDO.getLevel();
//            }
//        }
//
//        JwtUser jwtUser = new JwtUser(user.getUsername(), user.getId(), user.getPhone(), isAdmin, groupIds, groupLevel,user.getNickname());
//        Token token = null;
//        try {
//            token = JwtUtils.generateToken(jwtUser, tokenExpireTimeSecond, refreshTokenExpireTimeSecond);
//            String ck = String.format(RedisConstants.CACHE_REFRESH_TOKEN_VALID, jwtUser.getId());
//            redisTemplate.opsForValue().set(ck,true, refreshTokenExpireTimeSecond, TimeUnit.SECONDS);
//        } catch (Exception e) {
//            log.error(e.getMessage(), e);
//            throw new ServiceException(ExceptionEnumConstant.RUNTIME_EXCEPTION_ERROR);
//        }
//        return token;
//    }
}
