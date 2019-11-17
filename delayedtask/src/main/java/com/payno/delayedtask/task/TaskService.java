package com.payno.delayedtask.task;

import org.springframework.stereotype.Component;

import java.lang.annotation.*;

/**
 * @author payno
 * @date 2019/11/17 09:57
 * @description
 */
@Target({ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Component
public @interface TaskService {
    String value() default "";
}
