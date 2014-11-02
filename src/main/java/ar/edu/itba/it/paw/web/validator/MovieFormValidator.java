package ar.edu.itba.it.paw.web.validator;

import java.sql.Date;

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

	@SuppressWarnings("deprecation")
	@Override
	public void validate(Object target, Errors errors) {
		MovieForm obj = (MovieForm) target;
		if (errors.getFieldErrorCount("title") == 0 && obj.getTitle().isEmpty()) {
			errors.rejectValue("title", "empty");
		}
		if (errors.getFieldErrorCount("releaseDay") == 0 && (obj.getReleaseDay()< 0 || obj.getReleaseDay() > 31) ){
			errors.rejectValue("releaseDay", "invalid");
		}
		if (errors.getFieldErrorCount("releaseMonth") == 0 && (obj.getReleaseMonth()< 0 || obj.getReleaseMonth() > 12) ){
			errors.rejectValue("releaseMonth", "invalid");
		}
		int actualYear = new Date(System.currentTimeMillis()).getYear() + 1900; 
		if (errors.getFieldErrorCount("releaseYear") == 0 && (obj.getReleaseYear()< 1930 || obj.getReleaseYear() > actualYear) ){
			errors.rejectValue("releaseYear", "invalid");
		}
		if (errors.getFieldErrorCount("director") == 0 && obj.getDirector().isEmpty()) {
			errors.rejectValue("director", "empty");
		}
		if (errors.getFieldErrorCount("description") == 0 && obj.getDescription().isEmpty()) {
			errors.rejectValue("description", "empty");
		}
		if (errors.getFieldErrorCount("minutes") == 0 && obj.getMinutes() <= 0) {
			errors.rejectValue("minutes", "negative");
		}
		if(errors.getFieldErrorCount("genres") == 0 && obj.getGenres().size() <= 0){
			errors.rejectValue("genres", "empty");
		}

	}
}
