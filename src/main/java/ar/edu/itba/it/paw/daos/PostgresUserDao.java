package ar.edu.itba.it.paw.daos;

import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import ar.edu.itba.it.paw.models.User;

public class PostgresUserDao implements UserDao {

	private static PostgresUserDao obj = null;

	// Singleton
	private PostgresUserDao() {

	}

	public static UserDao getInstance() {
		if (obj == null)
			obj = new PostgresUserDao();

		return obj;
	}

	public Iterable<User> getAll() {
		Session<User> query = new Session<User>();
		ResultSet rs = query.list("user_table");
		List<User> users = new ArrayList<User>();

		try {
			while (rs.next()) {
				User user = createUser(rs);
				users.add(user);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return users;
	}

	public User getById(long id) {
		Session<User> query = new Session<User>();
		query.add(Criteria.eq("id", id));
		ResultSet rs = query.list("user_table");
		User user = null;
		try {
			if (rs.next()) {
				user = createUser(rs);
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return user;
	}

	public User getByEmail(String email) {
		Session<User> query = new Session<User>();
		query.add(Criteria.eq("email", email));
		ResultSet rs = query.list("user_table");
		User user = null;
		try {
			if (rs.next()) {
				user = createUser(rs);
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return user;
	}

	public void update(User user) {
		Session<User> session = new Session<User>();
		// In the database: id first_name last_name email password
		// secret_question secret_answer birth_date
		if (user.getId() > 0) {
			Object[] firstName = { "first_name", user.getFirstName() };
			Object[] lastName = { "last_name", user.getLastName() };
			Object[] email = { "email", user.getEmail() };
			Object[] password = { "password", user.getPassword() };
			Object[] birthDate = { "birth_date", user.getBirthDate() };
			session.add(Criteria.eq("id", user.getId()));
			session.update("user_table", firstName, lastName, email, password, birthDate);
		} else {
			session.insert("user_table", null, user.getFirstName(),
					user.getLastName(), user.getEmail(), user.getPassword(), null, null, user.getBirthDate());
		}

	}

	private User createUser(ResultSet rs) throws SQLException {
		String email = rs.getString("email");
		String password = rs.getString("password");
		String firstName = rs.getString("first_name");
		String lastName = rs.getString("last_name");
		Date birthDate = rs.getDate("birth_date");
		long id = rs.getLong("id");
		return new User(id, email, password, firstName, lastName, birthDate);
	}

}
