package com.example.demo.listener.user;

import com.alibaba.excel.context.AnalysisContext;
import com.alibaba.excel.event.AnalysisEventListener;
import com.alibaba.excel.util.StringUtils;
import com.example.demo.common.constants.ExceptionEnumConstant;
import com.example.demo.common.utils.EncryptUtil;
import com.example.demo.exception.ServiceException;
import com.example.demo.model.dto.user.UserDto;
import com.example.demo.model.entity.UserDO;
import com.example.demo.service.UserService;
import com.sun.org.apache.bcel.internal.generic.IF_ACMPEQ;
import org.springframework.beans.BeanUtils;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.regex.Pattern;

public class UserListener extends AnalysisEventListener<UserDto> {

    private final UserService userService;
    List<UserDto> list = new ArrayList<>();

    public UserListener(UserService userService) {
        this.userService = userService;
    }

    @Override
    public void invoke(UserDto userDto, AnalysisContext analysisContext) {
        if (StringUtils.isBlank(userDto.getUsername()) || StringUtils.isBlank(userDto.getPassword())) {
            throw new ServiceException(ExceptionEnumConstant.User_Password_IS_NULL);
        }
        if (StringUtils.isBlank(userDto.getPhone()) || !userDto.getPhone().matches("1[3-9]\\d{9}")) {
            throw new ServiceException(ExceptionEnumConstant.USER_PHONE_NOT_EMPTY);
        }
        if (StringUtils.isBlank(userDto.getEmail()) || !userDto.getEmail().matches("\\w{1,30}@[a-zA-Z0-9]{2,20}(\\.[a-zA-Z0-9]{2,20}){1,2}")) {
            throw new ServiceException(ExceptionEnumConstant.USER_Email_NOT_EMPTY);
        }
        list.add(userDto);
    }

    @Override
    public void doAfterAllAnalysed(AnalysisContext analysisContext) {
        List<UserDO> userList = new ArrayList<>();
        list.forEach(item -> {
            UserDO user = new UserDO();
            BeanUtils.copyProperties(item, user);
            user.setPassword(EncryptUtil.encrypt(item.getPassword())).setCreateTime(new Date());
            userList.add(user);
        });
        if (userList.size() > 0) userService.saveBatch(userList);
    }
}
