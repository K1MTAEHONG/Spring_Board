package com.example.demo.validator;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

import java.util.Arrays;
import java.util.List;

public class PreventFuxxValidator implements ConstraintValidator<Author,String> {

    List<String> fuxxList = Arrays.asList("씨발","시팔","fuck");


    @Override
    public boolean isValid(String field, ConstraintValidatorContext constraintValidatorContext){
        if(field ==null) {
            return true;
        }

        for (String f : fuxxList) {
            if(field != null && field.contains(f)) {
                return false;
            }
        }

        return true;
    }
}
