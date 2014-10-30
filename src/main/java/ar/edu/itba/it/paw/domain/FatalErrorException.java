package ar.edu.itba.it.paw.domain;

@SuppressWarnings("serial")
public class FatalErrorException extends RuntimeException{
	 public String getMessage(){
	        return "FATAL ERROR";
	    }
}
