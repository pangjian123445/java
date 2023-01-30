package com.example.demo.auth.annotations;

import java.lang.annotation.*;

/**
 * 有这个注解的controller方法都需要校验权限，如果mount为false只要登录了就可以
 */
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface PermissionMeta {

    String value();

//    @Deprecated
//    String permission() default "";

    String module() default "";

    boolean mount() default true;

}
