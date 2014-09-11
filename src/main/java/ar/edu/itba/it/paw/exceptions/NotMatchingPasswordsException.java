package ar.edu.itba.it.paw.exceptions;

@SuppressWarnings("serial")
public class NotMatchingPasswordsException extends Exception {
	
	 public String getMessage(){
	        return "The passwords don't match.";
	    }


}