package com.example.demo.service.impl;

import com.example.demo.model.entity.UserDO;
import com.example.demo.mapper.UserMapper;
import com.example.demo.model.vo.PageVo;
import com.example.demo.service.UserService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author generator@研发组
 * @since 2022-10-24
 */
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, UserDO> implements UserService {

    @Override
    public PageInfo<UserDO> getlist(UserDO user, String keyword, PageVo pageVo) {
        PageHelper.startPage(pageVo.getPageParam());
        List<UserDO> userList = this.baseMapper.getList(user,keyword);
        return new PageInfo<>(userList);
    }
}
