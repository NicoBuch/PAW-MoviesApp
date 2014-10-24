package ar.edu.itba.it.paw.domain;

@SuppressWarnings("serial")
public class EmptyFieldException extends Exception {
	
	 public String getMessage(){
	        return "The form can't have empty fields.";
	    }


}
