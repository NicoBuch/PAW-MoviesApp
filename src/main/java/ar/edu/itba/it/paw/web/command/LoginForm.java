package ar.edu.itba.it.paw.web.command;

public class LoginForm {
	String email;
	String password;
	
	LoginForm(String email, String password){
		this.email = email;
		this.password = password;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}
}
