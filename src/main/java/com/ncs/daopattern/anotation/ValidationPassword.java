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
@Constraint(validatedBy = ValidationPassword.PasswordValidator.class)
public @interface ValidationPassword {

    String message() default JdbcDaoPatternConstant.Validation.INVALID_PASSWORD;

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};


    class PasswordValidator implements ConstraintValidator<ValidationPassword, String> {

        @Override
        public boolean isValid(String value, ConstraintValidatorContext context) {
            return value.matches("^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*\\W).{6,32}$");
        }
    }
}
