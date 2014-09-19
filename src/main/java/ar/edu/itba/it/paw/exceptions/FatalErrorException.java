package ar.edu.itba.it.paw.exceptions;

@SuppressWarnings("serial")
public class FatalErrorException extends RuntimeException{
	 public String getMessage(){
	        return "FATAL ERROR";
	    }
}
