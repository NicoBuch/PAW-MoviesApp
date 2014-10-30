package ar.edu.itba.it.paw.command.validator;

import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import ar.edu.itba.it.paw.command.SignUpForm;

@Component
public class SignUpFormValidator implements Validator{

	@Override
	public boolean supports(Class<?> clazz) {
		return SignUpForm.class.equals(clazz);
	}
	@Override
	public void validate(Object target, Errors errors) {
		SignUpForm form =(SignUpForm) target;

		if (form.getFirstName() == null){
			errors.rejectValue("firstName", "empty");
		}
		if (form.getLastName() == null){
			errors.rejectValue("lastName", "empty");
		}
		if (form.getEmail() == null){
			errors.rejectValue("email", "empty");
		}
		if (form.getDay() == null){
			errors.rejectValue("day", "empty");
		}
		if (form.getMonth() == null){
			errors.rejectValue("month", "empty");
		}
		if (form.getYear() == null){
			errors.rejectValue("year", "empty");
		}
		if (form.getPassword() == null){
			errors.rejectValue("password", "empty");
		}
		if (form.getConfirmPassword() == null){
			errors.rejectValue("confirmPassword", "empty");
		}
		if (form.getSecretQuestion() == null){
			errors.rejectValue("secretQuestion", "empty");
		}
		if (form.getSecretAnswer() == null){
			errors.rejectValue("secretAnswer", "empty");
		}		
		
	}

}

	

