package ar.edu.itba.it.paw.web.validator;

import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import ar.edu.itba.it.paw.web.command.SignUpForm;

@Component
public class SignUpFormValidator implements Validator{

	@Override
	public boolean supports(Class<?> clazz) {
		return SignUpForm.class.equals(clazz);
	}
	@Override
	public void validate(Object target, Errors errors) {
		SignUpForm form =(SignUpForm) target;

		if (form.getFirstName().isEmpty()){
			errors.rejectValue("firstName", "empty", "Empty");
		}
		if (form.getLastName().isEmpty()){
			errors.rejectValue("lastName", "empty", "Empty");
		}
		if (form.getEmail().isEmpty()){
			errors.rejectValue("email", "empty", "Empty");
		}
		if (form.getBirthDay().isEmpty()){
			errors.rejectValue("day", "empty", "Empty");
		}
		if (form.getBirthMonth().isEmpty()){
			errors.rejectValue("month", "empty", "Empty");
		}
		if (form.getBirthYear().isEmpty()){
			errors.rejectValue("year", "empty", "Empty");
		}
		if (form.getPassword().isEmpty()){
			errors.rejectValue("password", "empty", "Empty");
		}
		if (form.getConfirmPassword().isEmpty()){
			errors.rejectValue("confirmPassword", "empty", "Empty");
		}
		if (form.getSecretQuestion().isEmpty()){
			errors.rejectValue("secretQuestion", "empty", "Empty");
		}
		if (form.getSecretAnswer().isEmpty()){
			errors.rejectValue("secretAnswer", "empty", "Empty");
		}		
		
	}

}

	

