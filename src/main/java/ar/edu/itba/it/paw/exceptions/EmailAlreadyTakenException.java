package ar.edu.itba.it.paw.exceptions;

@SuppressWarnings("serial")
public class EmailAlreadyTakenException extends Exception {
	
	 public String getMessage(){
	        return "The email is already in use.";
	    }


}
