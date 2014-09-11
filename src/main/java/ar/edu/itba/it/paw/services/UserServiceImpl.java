package ar.edu.itba.it.paw.services;

import ar.edu.itba.it.paw.daos.PostgresUserDao;
import ar.edu.itba.it.paw.daos.UserDao;
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

	public void save(User user) {

		userDao.save(user);
	}

//	public User registerUser(Map<String, String> data)
//			throws EmptyFieldException, EmailAlreadyTakenException,
//			NotMatchingPasswordsException {
//		if (hasEmptyRegisterForm(data)) {
//			throw new EmptyFieldException();
//		}
//		if (getByEmail(data.get("email")) != null) {
//			throw new EmailAlreadyTakenException();
//		}
//		if (!passwordMatch(data.get("password1"), data.get("password2"))) {
//			throw new NotMatchingPasswordsException();
//		}
//
//		Date birthDate = parseDate(data.get("birthDate"));
//
//		User user = new User(data.get("email"), data.get("password1"),
//				data.get("firstName"), data.get("lastName"), birthDate);
//		userDao.update(user);
//
//		// TODO auto-logear al usuario
//		return userDao.getByEmail(user.getEmail());
//
//	}

//	private boolean hasEmptyRegisterForm(Map<String, String> data) {
//		for (String key : data.keySet()) {
//			if (data.get(key).equals("")) {
//				return true;
//			}
//		}
//		return false;
//	}

//	private Date parseDate(String strDate) {
//
//		try {
//			// TODO use define for DateFormat?
//			SimpleDateFormat format = new SimpleDateFormat("yyyyMMdd");
//			java.util.Date parsed;
//			parsed = format.parse(strDate);
//			java.sql.Date birthDate = new java.sql.Date(parsed.getTime());
//			return birthDate;
//		} catch (Exception e) {
//			throw new InvalidBirthDateException();
//		}
//	}

	private boolean passwordMatch(String password1, String password2) {
		return password1.equals(password2);
	}

}
