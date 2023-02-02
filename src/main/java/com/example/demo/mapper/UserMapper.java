package com.example.demo.mapper;

import com.example.demo.model.entity.UserDO;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author generator@研发组
 * @since 2022-10-24
 */
public interface UserMapper extends BaseMapper<UserDO> {

    List<UserDO> getList(@Param("dto") UserDO user,@Param("keyword") String keyword);
}
