package ar.edu.itba.it.paw.domain.user;

@SuppressWarnings("serial")
public class EmailNotFound extends Exception {
	
	 public String getMessage(){
	        return "Email not found";
	    }


}
