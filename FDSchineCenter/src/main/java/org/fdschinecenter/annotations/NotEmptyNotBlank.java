package org.fdschinecenter.annotations;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;
import jakarta.validation.ReportAsSingleViolation;

import java.lang.annotation.*;

import static java.lang.annotation.ElementType.*;

/**
 * Custom validation annotation that combines the checks for non-null, non-empty, and non-blank values of a CharSequence.
 */
@Documented
@Constraint(validatedBy = {NotEmptyNotBlankValidator.class})
@Target({ METHOD, FIELD, ANNOTATION_TYPE, CONSTRUCTOR, PARAMETER, TYPE_USE })
@Retention(RetentionPolicy.RUNTIME)
@Repeatable(NotEmptyNotBlank.List.class)
@ReportAsSingleViolation
public @interface NotEmptyNotBlank {
    String message() default "{This field must not be empty or null or contain only whitespace characters}";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};

    /**
     * Defines several {@code @NotEmptyNotBlank} annotations on the same element.
     */
    @Documented
    @Target({ METHOD, FIELD, ANNOTATION_TYPE, CONSTRUCTOR, PARAMETER, TYPE_USE })
    @Retention(RetentionPolicy.RUNTIME)
    @interface List {
        NotEmptyNotBlank[] value();
    }
}