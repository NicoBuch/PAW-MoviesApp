package ar.edu.itba.it.paw.domain.user;

import java.util.List;

import ar.edu.itba.it.paw.domain.NoIdException;



public interface UserRepo {
	
	public List<User> getAll();
	
	public User getByEmail(String email) throws EmailNotFound;
	
	public User get(int id) throws NoIdException;
	
	public User login(String email, String password) throws LoginFailedException ;

	void save(User user);

}
