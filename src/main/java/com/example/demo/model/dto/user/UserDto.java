package com.example.demo.model.dto.user;

import com.alibaba.excel.annotation.ExcelProperty;
import lombok.Data;

@Data
public class UserDto {

    @ExcelProperty(value="用户名",index = 0)
    private String username;

    @ExcelProperty(value="密码",index = 1)
    private String password;

    @ExcelProperty(value="手机号",index = 2)
    private String phone;

    @ExcelProperty(value="邮箱",index = 3)
    private String email;

}
