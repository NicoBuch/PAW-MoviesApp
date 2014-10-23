package ar.edu.itba.it.paw.services;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;

import ar.edu.itba.it.paw.domain.AbstractHibernateRepo;
import ar.edu.itba.it.paw.domain.UserRepo;
import ar.edu.itba.it.paw.models.User;

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
}
