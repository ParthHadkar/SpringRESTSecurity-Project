package com.student_crm.validation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import javax.validation.Constraint;
import javax.validation.Payload;

import com.student_crm.contraintValidator.EmailConstraintValidator;

@Constraint(validatedBy = EmailConstraintValidator.class)
@Target({ElementType.METHOD,ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
public @interface EmailValidation {
	
	//define default value
	public String[] value() default {"@gmail.com","@yahoo.in","@yahoo.com"};
	//define default error message
	public String message() default "EmailId Not Valid";
	//define default groups
	public Class<?>[] groups() default {};
	//define default payload
	public Class<? extends Payload>[] payload() default {};
	
}
