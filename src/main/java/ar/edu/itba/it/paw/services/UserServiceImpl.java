package ar.edu.itba.it.paw.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ar.edu.itba.it.paw.daos.UserDao;
import ar.edu.itba.it.paw.exceptions.EmailAlreadyTakenException;
import ar.edu.itba.it.paw.exceptions.LoginFailedException;
import ar.edu.itba.it.paw.models.User;
@Service
public class UserServiceImpl implements UserService {

	private UserDao userDao;

	@Autowired
	public UserServiceImpl(UserDao userDao) {
		this.userDao = userDao;
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
	
	public boolean isVip(User user){
		return userDao.isVip(user);
	}

	public void establishNewPassword(User user,String newPassword){
		user.setPassword(newPassword);
		userDao.save(user);
	}

}
