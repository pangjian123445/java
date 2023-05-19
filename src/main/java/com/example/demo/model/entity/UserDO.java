package com.example.demo.model.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * <p>
 * 
 * </p>
 *
 * @author generator@研发组
 * @since 2022-10-24
 */
@Data
@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
@TableName("user")
public class UserDO extends BaseModel {


    /**
     * 用户名，唯一
     */
    private String username;

    /**
     * 用户昵称
     */
    private String nickname;

    //密码
    private String password;

    /**
     * 头像url
     */
    private String avatar;

    /**
     * 邮箱
     */
    private String email;

    private String phone;

    private Boolean isDelete;


}
