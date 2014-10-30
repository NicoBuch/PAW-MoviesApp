package ar.edu.itba.it.paw.domain;

public class Error {
	private String message= "";
	
	public Error() {
		// TODO Auto-generated constructor stub
	}
	public Error(String message) {
		this.message = message;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}
	
	
}
