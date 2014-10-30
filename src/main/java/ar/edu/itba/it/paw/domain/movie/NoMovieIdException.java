package ar.edu.itba.it.paw.domain.movie;

@SuppressWarnings("serial")
public class NoMovieIdException extends Exception{

	 public String getMessage(){
	        return "Invalid Id";
	    }

}
