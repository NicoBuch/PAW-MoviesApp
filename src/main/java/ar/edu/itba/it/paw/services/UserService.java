package ar.edu.itba.it.paw.services;

import ar.edu.itba.it.paw.exceptions.EmailAlreadyTakenException;
import ar.edu.itba.it.paw.exceptions.LoginFailedException;
import ar.edu.itba.it.paw.models.User;

public interface UserService {
	public User getByEmail(String email);

	public User login(String email, String password)
			throws LoginFailedException;

	public User getById(int id);

	public void save(User user) throws EmailAlreadyTakenException;
	
	public boolean isVip(User user);
	
	public void establishNewPassword(User user,String newPassword);

}
