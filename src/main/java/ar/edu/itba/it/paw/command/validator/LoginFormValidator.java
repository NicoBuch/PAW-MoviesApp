package ar.edu.itba.it.paw.command.validator;

import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import ar.edu.itba.it.paw.command.LoginForm;

@Component
public class LoginFormValidator implements Validator{

	@Override
	public boolean supports(Class<?> clazz) {
		return LoginForm.class.equals(clazz);
	}

	@Override
	public void validate(Object target, Errors errors) {
		LoginForm form =(LoginForm) target;
		
		//Aca checkear que sea un formato de email valido!
		//errors.rejectValue("email", "");
	
	}

}
