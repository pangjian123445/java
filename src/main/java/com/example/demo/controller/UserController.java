package com.example.demo.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.example.demo.auth.entity.Token;
import com.example.demo.common.constants.ExceptionEnumConstant;
import com.example.demo.common.response.Response;
import com.example.demo.common.response.ResponseGenerator;
import com.example.demo.common.utils.SendSMS;
import com.example.demo.config.LoginCaptchaProperties;
import com.example.demo.exception.ServiceException;
import com.example.demo.model.entity.UserDO;
import com.example.demo.model.vo.LoginVo;
import com.example.demo.model.vo.PageVo;
import com.example.demo.model.vo.PhoneLogin;
import com.example.demo.service.UserService;
import com.github.pagehelper.PageInfo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.util.StringUtils;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import java.io.IOException;
import java.util.Set;


@RestController
@RequestMapping("/user")
@Api(tags = "用户")
public class UserController {

    @Autowired
    private HttpSession session;

    @Autowired
    private UserService userService;

    @Autowired
    private LoginCaptchaProperties captchaConfig;

    @PostMapping("/getList")
    @ApiOperation("获取用户")
    private Response<?> getList(@RequestBody UserDO user, String keyword, PageVo pageVo){
        PageInfo<UserDO> result = userService.getlist(user,keyword,pageVo);
        return ResponseGenerator.genSuccessPageResult(result);
    }

    @PostMapping("/addUser")
    @ApiOperation("新增用户")
    private Response<?> addUser(@RequestBody @Valid UserDO user){
        userService.addUser(user);
        return ResponseGenerator.genSuccessResult();
    }

    @PostMapping("/updateUser")
    @ApiOperation("修改用户")
    private Response<?> updateUser(@RequestBody @Valid UserDO user){
        userService.updateUser(user);
        return ResponseGenerator.genSuccessResult();
    }

    @PostMapping("/delUser")
    @ApiOperation(value = "删除用户")
    public Response<?> delUser(@RequestBody Set<Long> ids) {
        userService.delUser(ids);
        return ResponseGenerator.genSuccessResult();
    }

    @PostMapping("/login")
    @ApiOperation(value = "登录")
    public Response<?> login(@RequestBody UserDO user) {
        return ResponseGenerator.genSuccessResult(userService.login(user));
    }

    @PostMapping("/register")
    @ApiOperation(value = "注册用户")
    public Response<?> register(@RequestBody @Valid UserDO userDO) {
        userService.register(userDO);
        return ResponseGenerator.genSuccessResult();
    }

    @PostMapping("/phoneLogin")
//    @ApiOperation(value = "123")
    public Response<?> phoneLogin(@RequestBody @Valid UserDO userDO) {
        userService.register(userDO);
        return ResponseGenerator.genSuccessResult();
    }

    @PostMapping("/sendCode")
    @ApiOperation(value = "发送手机验证码")
    public Response<?> sendCode(HttpServletRequest request, HttpServletResponse response, @RequestBody @Valid PhoneLogin phoneLogin) throws IOException {
        int code = SendSMS.sendYzCode(phoneLogin.getPhone());
        if (code != 0) {
            session = request.getSession();
            session.setAttribute("用id", phoneLogin.getPhone() + "#" + code);//1227346764#8765
            response.getWriter().print(true);
            response.reset();
        } else {
            response.getWriter().print(false);  //没短信了
            response.reset();
        }
        return ResponseGenerator.genSuccessResult();
    }
//https://open.weixin.qq.com/connect/oauth2/authorize?appid=wxdf4a5ec5661b9589&redirect_uri=http://localhost:8080/&response_type=code&scope=snsapi_userinfo&state=STATE&connect_redirect=1#wechat_redirect
    @PostMapping("/toPhoneLogin")
    @ApiOperation(value = "手机验证码登录")
    public Response<?> toPhoneLogin(HttpServletRequest request, HttpServletResponse response, @RequestBody @Valid PhoneLogin phoneLogin) throws IOException {
        String code1 = phoneLogin.getPhone() + "#" + phoneLogin.getCode();//页面提交的
        String code2 = (String) session.getAttribute("用id");//session中获取到的
        if (code1.equals(code2)) {
            //登陆成功，将登陆的用户保存到session中
            //根据用户的电话获取登陆用户
            UserDO loginUser = userService.getOne(new QueryWrapper<UserDO>().lambda().eq(UserDO::getPhone, phoneLogin.getPhone()).eq(UserDO::getIsDelete, false));
//            request.getSession()
            session.setAttribute("user", loginUser);
            response.getWriter().print(true);
            response.reset();
        } else {
            response.getWriter().print(false);
            response.reset();
        }
        return ResponseGenerator.genSuccessResult();
    }

//    @PostMapping("/login")
//    @ApiOperation(value = "登录")
//    public Response<?> login(@RequestBody @Validated LoginVo loginVo, @RequestHeader(value = "Tag", required = false) String tag) {
//        if (captchaConfig.getEnabled()) {
//            if (!StringUtils.hasText(loginVo.getCaptcha()) || !StringUtils.hasText(tag)) {
//                throw new ServiceException(ExceptionEnumConstant.VERIFICATION_CODE_EMPTY);
//            }
//            if (!userService.verifyCaptcha(loginVo.getCaptcha(), tag)) {
//                throw new ServiceException(ExceptionEnumConstant.VERIFICATION_CODE_ERROR);
//            }
//        }
//        Token token = userService.login(loginVo);
//        return ResponseGenerator.genSuccessResult(token);
//    }

}
