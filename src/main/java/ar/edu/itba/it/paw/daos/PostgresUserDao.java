package ar.edu.itba.it.paw.daos;

import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Repository;

import ar.edu.itba.it.paw.exceptions.FatalErrorException;
import ar.edu.itba.it.paw.models.User;
@Repository
public class PostgresUserDao implements UserDao {


	private PostgresUserDao() {

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
			query.close();
		} catch (SQLException e) {
			throw new FatalErrorException();
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
			query.close();

		} catch (SQLException e) {
			throw new FatalErrorException();
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
			query.close();

		} catch (SQLException e) {
			throw new FatalErrorException();
		}
		return user;
	}

	public void save(User user) {
		Session<User> session = new Session<User>();
		// In the database: id first_name last_name email password
		// secret_question secret_answer birth_date
		if (user.getId() > 0) {
			Object[] firstName = { "first_name", user.getFirstName() };
			Object[] lastName = { "last_name", user.getLastName() };
			Object[] email = { "email", user.getEmail() };
			Object[] password = { "password", user.getPassword() };
			Object[] birthDate = { "birth_date", user.getBirthDate() };
			Object[] secretQuestion = { "secret_question", user.getSecretQuestion() };
			Object[] secretAnswer = { "secret_Answer", user.getSecretAnswer() };
			session.add(Criteria.eq("id", user.getId()));
			session.update("user_table", firstName, lastName, email, password, birthDate,secretQuestion,secretAnswer);
		} else {
			session.insert("user_table", null, user.getFirstName(),
					user.getLastName(), user.getEmail(), user.getPassword(), user.getSecretQuestion(), user.getSecretAnswer(), user.getBirthDate());
		}
		try {
			session.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			throw new FatalErrorException();
		}

	}

	private User createUser(ResultSet rs) throws SQLException {
		String email = rs.getString("email");
		String password = rs.getString("password");
		String firstName = rs.getString("first_name");
		String lastName = rs.getString("last_name");
		Date birthDate = rs.getDate("birth_date");
		long id = rs.getLong("id");
		String secretQuestion = rs.getString("secret_question");
		String secretAnswer = rs.getString("secret_answer");
		boolean vip = rs.getBoolean("vip");
		User user = new User( email, password, firstName, lastName, birthDate,secretQuestion,secretAnswer,vip);
		user.setId(id);
		return user;
	}

	public boolean isVip(User user) {
		return user.isVip();
	}

}
