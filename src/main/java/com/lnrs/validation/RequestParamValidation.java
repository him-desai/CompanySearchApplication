package com.lnrs.validation;

import javax.validation.Constraint;
import javax.validation.Payload;
import javax.validation.ReportAsSingleViolation;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
// Not able to use this class, will update in git hub soon.
@Target({ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = RequestValidation.class)
@NotBlank(message = "Either companyName or companyNumber must be provided")
@NotNull(message = "Either companyName or companyNumber must be provided")
@ReportAsSingleViolation
public @interface RequestParamValidation {
    String message() default "Either companyName or companyNumber must be provided";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}
