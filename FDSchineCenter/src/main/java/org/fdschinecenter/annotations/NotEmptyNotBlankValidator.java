package org.fdschinecenter.annotations;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

/**
 * A custom constraint validator for the {@link NotEmptyNotBlank} annotation.
 */
public class NotEmptyNotBlankValidator implements ConstraintValidator<NotEmptyNotBlank, CharSequence> {

    @Override
    public void initialize(NotEmptyNotBlank constraintAnnotation) {
        // No initialization needed
    }

    /**
     * Validates the value of a CharSequence to check if it is not null, not empty, and not blank.
     * @param value
     * @param context
     * @return
     */
    @Override
    public boolean isValid(CharSequence value, ConstraintValidatorContext context) {
        if (value == null) {
            return false;
        }

        if (value instanceof String) {
            return !((String) value).trim().isEmpty() && !((String) value).trim().isBlank();
        }

        return true;
    }
}