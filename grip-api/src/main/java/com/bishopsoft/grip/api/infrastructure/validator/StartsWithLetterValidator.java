package com.bishopsoft.grip.api.infrastructure.validator;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class StartsWithLetterValidator implements ConstraintValidator<StartsWithLetterConstraint, String> {

    @Override
    public void initialize(StartsWithLetterConstraint constraintAnnotation) {

    }

    @Override
    public boolean isValid(String s, ConstraintValidatorContext constraintValidatorContext) {
        return s != null && Character.isLetter(s.charAt(0));
    }
}
