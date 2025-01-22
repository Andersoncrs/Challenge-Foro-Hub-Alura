package com.anderson.forohub.validate;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Constraint(validatedBy = MotivoValidator.class)
@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
public @interface ValidMotivo {
    String message() default "El Motivo ingresado no es Valido";
    Class<?>[] groups() default {}; // Para agrupación opcional
    Class<? extends Payload>[] payload() default {};
}
