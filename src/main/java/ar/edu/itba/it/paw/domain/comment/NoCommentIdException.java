package ar.edu.itba.it.paw.domain.comment;

public class NoCommentIdException extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	 public String getMessage(){
	        return "Invalid Id";
	    }

}
