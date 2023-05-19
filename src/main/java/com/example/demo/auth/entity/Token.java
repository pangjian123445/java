package com.example.demo.auth.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * @author qincq
 * @since 2022-2-9
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Token implements Serializable {

    private static final long serialVersionUID = 2041798077375663861L;

    private String token;

    private String refreshToken;

    /**
     * 用户名
     */
    private String username;

    /**
     * 到期时间
     */
    private LocalDateTime expireTime;

    private LocalDateTime refreshExpireTime;
}
