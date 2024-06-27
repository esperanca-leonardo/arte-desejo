package com.esperanca.projects.artedesejo.core.annotations;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.*;

@Documented
@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = ExistsValidator.class)
public @interface Exists {

  Class<?>[] groups() default {};

  Class<? extends Payload>[] payload() default {};

  String message() default "Object not found";
}
