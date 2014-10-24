package ar.edu.itba.it.paw.domain.user;

@SuppressWarnings("serial")
public class EmailAlreadyTakenException extends Exception {
	
	 public String getMessage(){
	        return "The email is already in use.";
	    }


}
