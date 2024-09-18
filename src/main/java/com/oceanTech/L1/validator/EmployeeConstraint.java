package com.oceanTech.L1.validator;

import static java.lang.annotation.ElementType.*;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;

@Target({TYPE})
@Retention(RUNTIME)
@Constraint(validatedBy = {EmployeeValidator.class})
public @interface EmployeeConstraint {
    String message() default "Invalid employee data";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}