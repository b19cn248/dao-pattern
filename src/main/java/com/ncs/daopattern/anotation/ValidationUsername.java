package com.ncs.daopattern.anotation;


import com.ncs.daopattern.constant.JdbcDaoPatternConstant;
import jakarta.validation.Constraint;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import jakarta.validation.Payload;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;


@Target({ ElementType.FIELD })
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = ValidationUsername.UsernameValidator.class)
public @interface ValidationUsername {

    String message() default JdbcDaoPatternConstant.Validation.INVALID_USERNAME;

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};


    class UsernameValidator implements ConstraintValidator<ValidationUsername, String> {

        @Override
        public boolean isValid(String value, ConstraintValidatorContext context) {
            return value.matches("^[a-z0-9\\.]+$");
        }
    }
}