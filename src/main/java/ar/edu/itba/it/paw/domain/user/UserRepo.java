package ar.edu.itba.it.paw.domain.user;



public interface UserRepo {
	
	public User getByEmail(String email);
	
	public User get(int id);
	
	public User login(String email, String password) throws LoginFailedException ;

}
