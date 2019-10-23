package com.example.user.aop;

import java.lang.annotation.*;

/**
 * @Author: 王能顺
 * @Date: 2019年10月23日11:28:47
 *
 * 自定义注解类
 */
@Target(ElementType.METHOD) //注解放置的目标位置,METHOD是可注解在方法级别上
@Retention(RetentionPolicy.RUNTIME) //注解在哪个阶段执行
@Documented //生成文档
public @interface MyLog {

    String value() default "";
}
