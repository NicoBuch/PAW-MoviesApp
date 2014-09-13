package ar.edu.itba.it.paw.services;

import ar.edu.itba.it.paw.daos.PostgresUserDao;
import ar.edu.itba.it.paw.daos.UserDao;
import ar.edu.itba.it.paw.exceptions.EmailAlreadyTakenException;
import ar.edu.itba.it.paw.exceptions.LoginFailedException;
import ar.edu.itba.it.paw.models.User;

public class UserServiceImpl implements UserService {

	private static UserServiceImpl obj = null;
	private static UserDao userDao = PostgresUserDao.getInstance();

	// Singleton
	private UserServiceImpl() {

	}

	public static UserServiceImpl getInstance() {
		if (obj == null)
			obj = new UserServiceImpl();
		return obj;
	}

	public User getByEmail(String email) {
		return userDao.getByEmail(email);
	}

	public User login(String email, String password)
			throws LoginFailedException {

		User user = userDao.getByEmail(email);
		if (user != null && passwordMatch(user.getPassword(), password)) {
			// Login OK.
			return user;
		} else {
			throw new LoginFailedException();
		}

	}

	public User getById(int id) {

		return userDao.getById(id);

	}

	public Iterable<User> getAll() {

		return userDao.getAll();

	}

	public void save(User user) throws EmailAlreadyTakenException {
		if (getByEmail(user.getEmail()) != null) {
			throw new EmailAlreadyTakenException();
		}
		userDao.save(user);
	}

	private boolean passwordMatch(String password1, String password2) {
		return password1.equals(password2);
	}

}
