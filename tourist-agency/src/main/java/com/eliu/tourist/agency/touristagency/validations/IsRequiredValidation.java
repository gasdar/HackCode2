package com.eliu.tourist.agency.touristagency.validations;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class IsRequiredValidation implements ConstraintValidator<IsRequired, String> {

    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {
        return (value != null && !value.isBlank());
    }
    
}
