package com.example.demo.controller;


import com.example.demo.common.response.Response;
import com.example.demo.common.response.ResponseGenerator;
import com.example.demo.model.entity.UserDO;
import com.example.demo.model.vo.PageVo;
import com.example.demo.service.UserService;
import com.github.pagehelper.PageInfo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/user")
@Api(tags = "用户")
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping("/getList")
    @ApiOperation("获取用户信息")
    private Response<?> getList(@RequestBody UserDO user, String keyword, PageVo pageVo){
        PageInfo<UserDO> result = userService.getlist(user,keyword,pageVo);
        return ResponseGenerator.genSuccessPageResult(result);
    }

}
