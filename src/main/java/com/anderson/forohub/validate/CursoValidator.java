package com.anderson.forohub.validate;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class CursoValidator implements ConstraintValidator<ValidCurso, String> {

    @Override
    public boolean isValid(String curso, ConstraintValidatorContext constraintValidatorContext) {
        if(curso == null){
            return true;
        }
        try {
            Curso.fromInputUser(curso.trim());
            return true;
        } catch (IllegalArgumentException e){
            return false;
        }
    }
}
