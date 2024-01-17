package dev.dmohindru.demostudentapi.validation;

import dev.dmohindru.demostudentapi.constants.CourseID;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class CourseValidator implements ConstraintValidator<CourseValidation, String> {
    @Override
    public boolean isValid(String s, ConstraintValidatorContext constraintValidatorContext) {
        try {
            CourseID.valueOf(s);
        } catch (IllegalArgumentException ex) {
            return false;
        }
        return true;
    }
}
