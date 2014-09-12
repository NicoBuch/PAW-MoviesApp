package ar.edu.itba.it.paw.exceptions;

@SuppressWarnings("serial")
public class NoMovieIdException extends Exception{

	 public String getMessage(){
	        return "Invalid Id";
	    }

}
