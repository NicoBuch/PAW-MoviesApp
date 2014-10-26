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

		//Aca checkear que sea un formato de email valido!
		//errors.rejectValue("email", "");
		
	}

}

	

