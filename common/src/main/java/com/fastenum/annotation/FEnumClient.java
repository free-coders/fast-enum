package com.fastenum.annotation;

import java.lang.annotation.*;

/**
 * @Documented – 注解是否将包含在JavaDoc中
 * @Retention – 什么时候使用该注解
 * @Target – 注解用于什么地方
 * @Inherited – 是否允许子类继承该注解
 *
 * @Description : 远程调用注释类
 * @Author : zhangMing
 * @Date : Created in 10:24 2020-09-30
 */
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.TYPE})
@Documented
public @interface FEnumClient {

    String value() default "";

}
