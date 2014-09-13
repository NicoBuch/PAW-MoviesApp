package ar.edu.itba.it.paw.exceptions;

@SuppressWarnings("serial")
public class NoGenreException extends Exception{
	 public String getMessage(){
	        return "No existe tal genero";
	    }
}
