package com.cjb.txtransactional.annotation;

import java.lang.annotation.*;

@Target({ElementType.TYPE, ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
@Inherited
@Documented
public @interface CjbTransactional {

    boolean isStart() default false;
    boolean isEnd() default false;
}