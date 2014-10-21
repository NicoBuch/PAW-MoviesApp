package ar.edu.itba.it.paw.domain;

import ar.edu.itba.it.paw.exceptions.LoginFailedException;
import ar.edu.itba.it.paw.models.User;

public interface UserRepo {
	
	public User getByEmail(String email);
	
	public User get(int id);
	
	public User login(String email, String password) throws LoginFailedException ;

}
