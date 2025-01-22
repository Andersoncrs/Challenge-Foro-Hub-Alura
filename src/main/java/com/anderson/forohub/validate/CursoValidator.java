package com.anderson.forohub.validate;

import com.anderson.forohub.domain.topico.Curso;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

import java.util.Arrays;

public class CursoValidator implements ConstraintValidator<ValidCurso, String> {

    @Override
    public boolean isValid(String curso, ConstraintValidatorContext constraintValidatorContext) {
        if(curso == null){
            return true;
        }
        try {
            Curso.valueOf(curso);
            return true;
        } catch (IllegalArgumentException e){
            return false;
        }
    }
}
