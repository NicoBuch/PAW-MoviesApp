package ar.edu.itba.it.paw.web.validator;

import java.sql.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.regex.Pattern;

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
		Pattern rfc2822 = Pattern.compile(
		        "^[a-z0-9!#$%&'*+/=?^_`{|}~-]+(?:\\.[a-z0-9!#$%&'*+/=?^_`{|}~-]+)*@(?:[a-z0-9](?:[a-z0-9-]*[a-z0-9])?\\.)+[a-z0-9](?:[a-z0-9-]*[a-z0-9])?$"
		);

		if (form.getFirstName().isEmpty()){
			errors.rejectValue("firstName", "empty", "First Name is a required field.");
		}else if (form.getFirstName().length() > 255){
			errors.rejectValue("firstName", "tooLong", "First Name is too long (Max 255).");
		}
		
		if (form.getLastName().isEmpty()){
			errors.rejectValue("lastName", "empty", "Last Name is a required field.");
		}else if (form.getLastName().length() > 255){
			errors.rejectValue("lastName", "tooLong", "Last Name is too long (Max 255).");
		}
		
		if (form.getEmail().isEmpty()){
			errors.rejectValue("email", "empty", "Email is a required field.");
		}else if (form.getEmail().length() > 255){
			errors.rejectValue("email", "tooLong", "Email is too long (Max 255).");
		} else if (!rfc2822.matcher(form.getEmail()).matches()) {
			errors.rejectValue("email", "invalid", "The email is invalid.");
		}
		
		if (form.getBirthDay() == 0 || form.getBirthMonth() == 0 || form.getBirthYear() == 0){
			errors.rejectValue("birthDay", "empty", "Birth Date is incomplete.");
		}else{
			String birthDate = form.getBirthYear() + "-" + form.getBirthMonth() + "-" + form.getBirthDay();			
			if(! isValidDate(birthDate)){
				errors.rejectValue("birthDay", "invalid", "Birth Date is invalid.");
			}else if(Date.valueOf(birthDate).after(new Date(System.currentTimeMillis()))){
				errors.rejectValue("birthDay", "invalid", "Birth Date is invalid.");
			}
		}
		
		if (form.getPassword().isEmpty()){
			errors.rejectValue("password", "empty", "Password is a required field.");
		}else if (form.getConfirmPassword().isEmpty()){
			errors.rejectValue("confirmPassword", "empty", "Password Confirmation is a required field.");		
		}else if (!form.getPassword().equals(form.getConfirmPassword())){
			errors.rejectValue("confirmPassword", "notMatch", "Passwords does not match.");
		}else if (form.getPassword().length() > 255){
			errors.rejectValue("password", "tooLong", "Password is too long (Max 255).");
		} 
		
		if (form.getSecretQuestion().isEmpty()){
			errors.rejectValue("secretQuestion", "empty", "Secret Question is a required field.");
		}else if (form.getSecretQuestion().length() > 255){
			errors.rejectValue("secretQuestion", "tooLong", "Secret Question is too long (Max 255).");
		}else if (!form.getSecretQuestion().endsWith("?")){
			errors.rejectValue("secretQuestion", "wrongTermination", "Secret Question must end with '?'.");
		}
		
		if (form.getSecretAnswer().isEmpty()){
			errors.rejectValue("secretAnswer", "empty", "Secret Answer is a required field.");
		}else if (form.getSecretAnswer().length() > 255){
			errors.rejectValue("secretAnswer", "tooLong", "Secret Answer is too long (Max 255).");
		}	
		
	}
	
	public static boolean isValidDate(String inDate) {
	    SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
	    dateFormat.setLenient(false);
	    try {
	      dateFormat.parse(inDate.trim());
	    } catch (ParseException pe) {
	      return false;
	    }
	    return true;
	}

}

	

