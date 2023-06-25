package com.mrsystem.util;

import static java.lang.annotation.ElementType.*;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

import com.mrsystem.modelo.enums.EValidacao;
import jakarta.validation.Constraint;
import jakarta.validation.Payload;
import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

@Target({METHOD, FIELD, ANNOTATION_TYPE, CONSTRUCTOR, PARAMETER, TYPE_USE})
@Retention(RUNTIME)
@Documented
@Constraint(validatedBy = EnumValidoValidator.class)
public @interface EnumValido {
    Class<? extends Enum<?>> enumClass();

    String message() default "must be any of enum {enumClass}";

    EValidacao validacao();

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}
