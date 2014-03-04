package org.training.issuetracker.validation;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import javax.validation.Constraint;
import javax.validation.ReportAsSingleViolation;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.NotBlank;

//@NotBlank
//@Size(min = 6)
//@Pattern(regexp="[A-Za-z0-9._%+-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,4}")
@Target(ElementType.PARAMETER)
// specifies where this validation can be used (Field, Method, Parameter etc)
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Constraint(validatedBy=EmailConstraintValidator.class)
//@ReportAsSingleViolation
// specifies if any of the validation fails, it will be reported as single validation

public @interface CheckEmail {
	
	/**
     * This is the key to message will  be looked in validation.properties for validation
     * errors
     * 
     * @return the string
     */
    String message() default "{email.invalid}";
 
    Class[] groups() default {};
 
    Class[] payload() default {};
}
