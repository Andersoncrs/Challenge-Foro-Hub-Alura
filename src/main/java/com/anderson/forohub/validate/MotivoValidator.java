package com.anderson.forohub.validate;

import com.anderson.forohub.domain.topico.Motivo;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class MotivoValidator implements ConstraintValidator<ValidMotivo, String> {
    @Override
    public boolean isValid(String motivo, ConstraintValidatorContext constraintValidatorContext) {
        if (motivo == null){
            return true;
        }
        try{
            Motivo.fromInputUser(motivo.trim());
            return true;
        } catch (IllegalArgumentException e) {
            return false;
        }
    }
}
