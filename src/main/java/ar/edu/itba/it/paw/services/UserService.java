package ar.edu.itba.it.paw.services;

import java.sql.Date;
import java.text.SimpleDateFormat;
import java.util.Map;

import ar.edu.itba.it.paw.daos.PostgresUserDao;
import ar.edu.itba.it.paw.daos.UserDao;
import ar.edu.itba.it.paw.exceptions.EmailAlreadyTakenException;
import ar.edu.itba.it.paw.exceptions.EmptyFieldException;
import ar.edu.itba.it.paw.exceptions.InvalidBirthDateException;
import ar.edu.itba.it.paw.exceptions.LoginFailedException;
import ar.edu.itba.it.paw.exceptions.NotMatchingPasswordsException;
import ar.edu.itba.it.paw.models.User;

public class UserService {

	private static UserService obj = null;
	private static UserDao userDao = PostgresUserDao.getInstance();

	// Singleton
	private UserService() {

	}

	public static UserService getInstance() {
		if (obj == null)
			obj = new UserService();
		return obj;
	}

	public User getByEmail(String email) {
		return userDao.getByEmail(email);
	}

	public User login(String email, String password) {

		User user = userDao.getByEmail(email);
		if (user != null && passwordMatch(user.getPassword(), password)) {
			//Login OK.
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

	public void updateUser(String email, String password, String firstName,
			String lastName, Date birthDate) {

		User user = new User(email, password, firstName, lastName, birthDate);
		userDao.update(user);
	}

	
	public User registerUser(Map<String, String> data) {
		if (hasEmptyRegisterForm(data)) {
			throw new EmptyFieldException();
		}
		if (getByEmail(data.get("email")) != null) {
			throw new EmailAlreadyTakenException();
		}
		if (!passwordMatch(data.get("password1"), data.get("password2"))) {
			throw new NotMatchingPasswordsException();
		}

		
		Date birthDate = parseDate(data.get("birthDate"));
		
		User user = new User(data.get("email"), data.get("password1"), data.get("firstName"), data.get("lastName"), birthDate);
		userDao.update(user);
		
		//TODO auto-logear al usuario
		return userDao.getByEmail(user.getEmail());

	}
	
	private boolean hasEmptyRegisterForm(Map<String, String> data) {
		for (String key : data.keySet()) {
			if (data.get(key).equals("")) {
				return true;
			}
		}
		return false;
	}

	private Date parseDate(String strDate) {

		try {
			//TODO use define for DateFormat?
			SimpleDateFormat format = new SimpleDateFormat("yyyyMMdd");
			java.util.Date parsed;
			parsed = format.parse(strDate);
			java.sql.Date birthDate = new java.sql.Date(parsed.getTime());
			return birthDate;
		} catch (Exception e) {
			throw new InvalidBirthDateException();
		}
	}
	
	private boolean passwordMatch(String password1, String password2) {
		return password1.equals(password2);
	}

}
