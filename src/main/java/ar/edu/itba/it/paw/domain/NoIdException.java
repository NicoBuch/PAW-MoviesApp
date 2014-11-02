package ar.edu.itba.it.paw.domain;

public class NoIdException extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	 public String getMessage(){
	        return "There is no object with the id you are looking for";
	    }

}
