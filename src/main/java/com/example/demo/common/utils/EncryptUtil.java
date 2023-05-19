package com.example.demo.common.utils;

import com.amdelamar.jhash.Hash;
import com.amdelamar.jhash.algorithms.Type;
import com.amdelamar.jhash.exception.InvalidHashException;
import com.example.demo.common.constants.ExceptionEnumConstant;
import com.example.demo.exception.ServiceException;
import lombok.extern.slf4j.Slf4j;

import java.util.regex.Pattern;

@Slf4j
public class EncryptUtil {

    public static final Pattern pattern = Pattern.compile("^(?![a-zA-Z]+$)(?![A-Z0-9]+$)(?![A-Z\\W_]+$)(?![a-z0-9]+$)(?![a-z\\W_]+$)(?![0-9\\W_]+$)[a-zA-Z0-9\\W_]{8,}$");

    /**
     * 设置密文密码
     *
     * @param password 原始密码
     * @return 加密密码
     */
    public static String encrypt(String password) {
        char[] chars = password.toCharArray();
        return Hash.password(chars).algorithm(Type.PBKDF2_SHA256).create();
    }

    /**
     * 验证加密密码
     *
     * @param encryptedPassword 密文密码
     * @param plainPassword     明文密码
     * @return 验证是否成功
     */
    public static boolean verify(String encryptedPassword, String plainPassword) {
        char[] chars = plainPassword.toCharArray();
        try {
            return Hash.password(chars).algorithm(Type.PBKDF2_SHA256).verify(encryptedPassword);
        } catch (InvalidHashException e) {
            log.error(e.getMessage(),e);
            return false;
        }
    }

    /**
     * 校验是否是过于简单的密码
     * @param pwd
     */
    public static void checkPwd(String pwd) {
        if (!pattern.matcher(pwd).find() ||
                (pwd.length() == 8 && (pwd.contains("123456") || pwd.contains("234567")))) {
            throw new ServiceException(ExceptionEnumConstant.USER_PASSWORD_ILLEGAL);
        }
    }
}
