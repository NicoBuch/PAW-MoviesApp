package ar.edu.itba.it.paw.domain.user;

@SuppressWarnings("serial")
public class NotMatchingPasswordsException extends Exception {
	
	 public String getMessage(){
	        return "The passwords don't match.";
	    }


}