package ar.edu.itba.it.paw.domain;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import ar.edu.itba.it.paw.exceptions.LoginFailedException;
import ar.edu.itba.it.paw.models.User;

@Repository
public class HibernateUserRepo extends AbstractHibernateRepo implements UserRepo{
	@Autowired
	public HibernateUserRepo(SessionFactory sessionFactory) {
		super(sessionFactory);
	}
	
	public User get(int id) {
		return get(User.class, id);
	}

	public User getByEmail(String email) {
		return (User) find("from User where email = ?", email).get(0);
	}

	public User login(String email, String password)
			throws LoginFailedException {

		User user = getByEmail(email);
		if (user != null && user.getPassword().equals(password)) {
			return user;
		} else {
			throw new LoginFailedException();
		}

	}
}

