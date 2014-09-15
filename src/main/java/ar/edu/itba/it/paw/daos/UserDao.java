package ar.edu.itba.it.paw.daos;

import ar.edu.itba.it.paw.models.User;

public interface UserDao {
	
	public abstract Iterable<User> getAll();
	public abstract User getById(long id);
	public abstract User getByEmail(String email);
	public abstract void save(User user);
	public abstract boolean isVip(User user);
   
	
}