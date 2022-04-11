package com.adamin.demo.intercepter;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @Classname UnInterceptor
 * @Description 使MyInterceptor不拦截的方法
 * @Date 2022/4/11 12:13
 * @Created by Adam(https://www.lixiaopeng.top)
 */
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
public @interface UnInterceptor {
}
