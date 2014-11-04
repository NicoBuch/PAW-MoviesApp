package ar.edu.itba.it.paw.web.validator;


import java.text.ParseException;
import java.text.SimpleDateFormat;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import ar.edu.itba.it.paw.web.command.MovieForm;

@Component
public class MovieFormValidator implements Validator {

	@Override
	public boolean supports(Class<?> clazz) {
		return MovieForm.class.equals(clazz);
	}


	@Override
	public void validate(Object target, Errors errors) {
		MovieForm form = (MovieForm) target;
		
		if (form.getTitle().isEmpty()) {
			errors.rejectValue("title", "empty", "Title is a required field.");
		}else if(form.getTitle().length() > 255){
			errors.rejectValue("title", "tooLong", "Title is too long (Max 255).");
		}
		
		if (form.getDirector().isEmpty()) {
			errors.rejectValue("director", "empty", "Director is a required field.");
		}else if(form.getDirector().length() > 255){
			errors.rejectValue("director", "tooLong", "Director is too long (Max 255).");
		}
		
		if (form.getReleaseDay() == 0 || form.getReleaseMonth() == 0 || form.getReleaseYear() == 0){
			errors.rejectValue("releaseDay", "empty", "Release Date is incomplete.");
		}else{
			String birthDate = form.getReleaseYear() + "-" + form.getReleaseMonth() + "-" + form.getReleaseDay();			
			if(! isValidDate(birthDate)){
				errors.rejectValue("releaseDay", "invalid", "Release Date is invalid.");
			}
		}
				
		if (form.getDescription().isEmpty()) {
			errors.rejectValue("description", "empty", "Description is a required field.");
		}
		
		if (form.getMinutes() <= 0 || form.getMinutes() == 0) {
			errors.rejectValue("minutes", "invalid", "Duration is invalid.");
		}
		if(form.getGenres() == null || form.getGenres().size() <= 0){
			errors.rejectValue("genres", "empty", "Please select a genre.");
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
