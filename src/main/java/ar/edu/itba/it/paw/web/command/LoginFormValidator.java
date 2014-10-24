package ar.edu.itba.it.paw.web.command;

import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

@Component
public class LoginFormValidator implements Validator{

	public boolean supports(Class<?> clazz) {
		return LoginForm.class.equals(clazz);
	}

	public void validate(Object target, Errors errors) {
		LoginForm form =(LoginForm) target;
		
		//Aca checkear que sea un formato de email valido!
		//errors.rejectValue("email", "");
	
	}

}
