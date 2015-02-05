package ar.edu.itba.it.paw.domain.movie;

@SuppressWarnings("serial")
public class NoStockAvailableException extends Exception {
	 public String getMessage(){
	        return "There is not available information about the requested movie stock";
	    }
}
