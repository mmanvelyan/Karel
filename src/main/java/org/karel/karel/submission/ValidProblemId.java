package org.karel.karel.submission;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import static java.lang.annotation.ElementType.*;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

@Target({ FIELD, METHOD, PARAMETER, ANNOTATION_TYPE })
@Retention(RUNTIME)
@Constraint(validatedBy = ProblemIdValidator.class)
@Documented
public @interface ValidProblemId {
    String message() default "Invalid Problem Id";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}
