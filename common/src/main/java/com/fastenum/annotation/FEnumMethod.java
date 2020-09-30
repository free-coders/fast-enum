package com.fastenum.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

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
@Target({ElementType.METHOD})
public @interface FEnumMethod {

    String value() default "";

}
