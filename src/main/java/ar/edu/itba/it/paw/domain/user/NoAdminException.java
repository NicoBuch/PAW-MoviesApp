package ar.edu.itba.it.paw.domain.user;

public class NoAdminException extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
    public String getMessage(){
        return "Only Admin can do this action.";
    }

}
