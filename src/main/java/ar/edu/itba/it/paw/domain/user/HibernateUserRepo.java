package ar.edu.itba.it.paw.domain.user;

import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import ar.edu.itba.it.paw.domain.AbstractHibernateRepo;
import ar.edu.itba.it.paw.domain.NoIdException;

@Repository
public class HibernateUserRepo extends AbstractHibernateRepo implements UserRepo{
	@Autowired
	public HibernateUserRepo(SessionFactory sessionFactory) {
		super(sessionFactory);
	}

	public List<User> getAll() {
		return find("from User");
	}

	public User get(int id) throws NoIdException {
		try{
			return get(User.class, id);
		} catch (HibernateException e) {
			throw new NoIdException();
		}
	}

	public User getByEmail(String email) throws EmailNotFound {
		try{
			return (User) find("from User where email = ?", email).get(0);
		}catch(IndexOutOfBoundsException e){
			throw new EmailNotFound();
		}
	}

	public User login(String email, String password)
			throws LoginFailedException {
		User user;
		try{
			user = getByEmail(email);
		}catch(EmailNotFound e){
			throw new LoginFailedException();
		}
		if (user != null && user.getPassword().equals(password)) {
			return user;
		} else {
			throw new LoginFailedException();
		}

	}

	@Override
	public void save(User user){
		super.save(user);
	}

}

