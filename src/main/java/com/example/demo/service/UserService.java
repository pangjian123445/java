package com.example.demo.service;

import com.example.demo.model.entity.UserDO;
import com.baomidou.mybatisplus.extension.service.IService;
import com.example.demo.model.vo.PageVo;
import com.github.pagehelper.PageInfo;

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
}
