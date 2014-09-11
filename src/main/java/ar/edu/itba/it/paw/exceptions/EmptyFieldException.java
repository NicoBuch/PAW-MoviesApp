package ar.edu.itba.it.paw.exceptions;

@SuppressWarnings("serial")
public class EmptyFieldException extends RuntimeException {
	
	 public String getMessage(){
	        return "The form can't have empty fields.";
	    }


}
