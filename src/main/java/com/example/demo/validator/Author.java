package com.example.demo.validator;


import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy =AuthorValidator.class )
public @interface Author {
    String message() default "관리자를  사칭 할 수 없습니다.";

    Class<?>[] groups() default { };

    Class<? extends Payload>[] payload() default { };

}
