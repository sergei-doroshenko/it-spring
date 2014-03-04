package org.training.issuetracker.validation;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import javax.validation.Constraint;

@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Constraint(validatedBy=UserConstraintValidator.class)
public @interface CheckUser {
	String message() default "{user.invalid}";
	 
    Class[] groups() default {};
 
    Class[] payload() default {};

}
