package com.example.demo.mapper;

import com.example.demo.model.dto.user.UserDto;
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

    /**
     * 查询用户名为$username的人数
     *
     * @param username 用户名
     * @return 人数
     */
    int selectCountByUsername(String username);

    /**
     * 查询用户id为$id的人数
     *
     * @param id 用户id
     * @return 人数
     */
    int selectCountById(Long id);

    List<UserDto> getExportList(@Param("dto") UserDO user,@Param("keyword") String keyword);
}
