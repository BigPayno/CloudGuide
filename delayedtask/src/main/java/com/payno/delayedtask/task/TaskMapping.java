package com.payno.delayedtask.task;

import java.lang.annotation.*;

/**
 * @author payno
 * @date 2019/11/17 10:06
 * @description
 */
@Target({ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface TaskMapping {
    String serviceId() default "";
    Class<?> param() default Object.class;
    Class<?> result() default Void.class;
}
