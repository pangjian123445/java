package com.example.demo.service;

import com.example.demo.auth.entity.Token;
import com.example.demo.model.entity.UserDO;
import com.baomidou.mybatisplus.extension.service.IService;
import com.example.demo.model.vo.LoginVo;
import com.example.demo.model.vo.PageVo;
import com.github.pagehelper.PageInfo;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Set;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author generator@研发组
 * @since 2022-10-24
 */
public interface UserService extends IService<UserDO> {

    PageInfo<UserDO> getlist(UserDO user, String keyword, PageVo pageVo);

    void addUser(UserDO user);

    void updateUser(UserDO user);

    void delUser(Set<Long> ids);

    /**
     * 校验登录验证码
     *
     * @return 结果
     */
    boolean verifyCaptcha(String captcha, String tag);

//    /**
//     * 登录
//     * @param loginVo
//     * @return
//     */
//    Token login(LoginVo loginVo);

    UserDO login(UserDO user);

    void register(UserDO userDO);

    /**
     * 通过用户名查找用户
     *
     * @param username 用户名
     * @return 用户
     */
    UserDO getUserByUsername(String username);

    /**
     * 根据用户名检查用户是否存在
     *
     * @param username 用户名
     * @return true代表存在
     */
    boolean checkUserExistByUsername(String username);


    /**
     * 根据用户名检查用户是否存在
     *
     * @param email 邮箱
     * @return true代表存在
     */
    boolean checkUserExistByEmail(String email);

    /**
     * 根据用户id检查用户是否存在
     *
     * @param id 用户名
     * @return true代表存在
     */
    boolean checkUserExistById(Long id);

    void importBindUser(MultipartFile file) throws IOException;

    void exportUser(HttpServletResponse response, UserDO dto, String keyword);
}
