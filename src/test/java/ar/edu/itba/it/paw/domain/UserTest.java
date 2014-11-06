package ar.edu.itba.it.paw.domain;

import java.sql.Date;

import org.junit.Assert;
import org.junit.Test;

import ar.edu.itba.it.paw.domain.movie.Movie;
import ar.edu.itba.it.paw.domain.user.User;

public class UserTest {

	@Test(expected = IllegalArgumentException.class)
	public void tooLongFirstNameTest() {
		new User(
				"email@email.com",
				"password",
				"firstNamefirstNamefirstNamefirstNamefirstNamefirstNamefirstNamefirstNamefirstNamefirstNamefirstNamefirstNamefirstNamefirstNamefirstNamefirstNamefirstNamefirstNamefirstNamefirstNamefirstNamefirstNamefirstNamefirstNamefirstNamefirstNamefirstNamefirstNamefirstNamefirstNamefirstNamefirstNamefirstNamefirstNamefirstNamefirstNamefirstNamefirstNamefirstNamefirstNamefirstNamefirstNamefirstNamefirstNamefirstNamefirstNamefirstNamefirstNamefirstNamefirstNamefirstNamefirstNamefirstNamefirstNamefirstNamefirstNamefirstNamefirstNamefirstNamefirstNamefirstNamefirstNamefirstNamefirstNamefirstNamefirstNamefirstNamefirstNamefirstNamefirstNamefirstNamefirstNamefirstNamefirstNamefirstNamefirstNamefirstNamefirstNamefirstNamefirstNamefirstNamefirstNamefirstNamefirstNamefirstNamefirstNamefirstNamefirstNamefirstNamefirstNamefirstNamefirstNamefirstNamefirstNamefirstNamefirstNamefirstNamefirstNamefirstNamefirstNamefirstNamefirstNamefirstNamefirstNamefirstNamefirstNamefirstNamefirstNamefirstNamefirstNamefirstNamefirstNamefirstNamefirstNamefirstNamefirstName",
				"lastName", new Date(System.currentTimeMillis()), "hola?",
				"hola", false);
	}

	@Test(expected = IllegalArgumentException.class)
	public void tooLongLastNameTest() {
		new User(
				"email@email.com",
				"password",
				"firstName",
				"firstNamefirstNamefirstNamefirstNamefirstNamefirstNamefirstNamefirstNamefirstNamefirstNamefirstNamefirstNamefirstNamefirstNamefirstNamefirstNamefirstNamefirstNamefirstNamefirstNamefirstNamefirstNamefirstNamefirstNamefirstNamefirstNamefirstNamefirstNamefirstNamefirstNamefirstNamefirstNamefirstNamefirstNamefirstNamefirstNamefirstNamefirstNamefirstNamefirstNamefirstNamefirstNamefirstNamefirstNamefirstNamefirstNamefirstNamefirstNamefirstNamefirstNamefirstNamefirstNamefirstNamefirstNamefirstNamefirstNamefirstNamefirstNamefirstNamefirstNamefirstNamefirstNamefirstNamefirstNamefirstNamefirstNamefirstNamefirstNamefirstNamefirstNamefirstNamefirstNamefirstNamefirstNamefirstNamefirstNamefirstNamefirstNamefirstNamefirstNamefirstNamefirstNamefirstNamefirstNamefirstNamefirstNamefirstNamefirstNamefirstNamefirstNamefirstNamefirstNamefirstNamefirstNamefirstNamefirstNamefirstNamefirstNamefirstNamefirstNamefirstNamefirstNamefirstNamefirstNamefirstNamefirstNamefirstNamefirstNamefirstNamefirstNamefirstNamefirstNamefirstNamefirstNamefirstNamefirstName",
				new Date(System.currentTimeMillis()), "hola?", "hola", false);
	}

	@Test(expected = IllegalArgumentException.class)
	public void tooLongPasswordTest() {
		new User(
				"email@email.com",
				"firstNamefirstNamefirstNamefirstNamefirstNamefirstNamefirstNamefirstNamefirstNamefirstNamefirstNamefirstNamefirstNamefirstNamefirstNamefirstNamefirstNamefirstNamefirstNamefirstNamefirstNamefirstNamefirstNamefirstNamefirstNamefirstNamefirstNamefirstNamefirstNamefirstNamefirstNamefirstNamefirstNamefirstNamefirstNamefirstNamefirstNamefirstNamefirstNamefirstNamefirstNamefirstNamefirstNamefirstNamefirstNamefirstNamefirstNamefirstNamefirstNamefirstNamefirstNamefirstNamefirstNamefirstNamefirstNamefirstNamefirstNamefirstNamefirstNamefirstNamefirstNamefirstNamefirstNamefirstNamefirstNamefirstNamefirstNamefirstNamefirstNamefirstNamefirstNamefirstNamefirstNamefirstNamefirstNamefirstNamefirstNamefirstNamefirstNamefirstNamefirstNamefirstNamefirstNamefirstNamefirstNamefirstNamefirstNamefirstNamefirstNamefirstNamefirstNamefirstNamefirstNamefirstNamefirstNamefirstNamefirstNamefirstNamefirstNamefirstNamefirstNamefirstNamefirstNamefirstNamefirstNamefirstNamefirstNamefirstNamefirstNamefirstNamefirstNamefirstNamefirstNamefirstNamefirstNamefirstName",
				"firstName", "lasName", new Date(System.currentTimeMillis()),
				"hola?", "hola", false);
	}

	@Test(expected = IllegalArgumentException.class)
	public void nullEmailTest() {
		new User(null, "password", "firstName", "lasName", new Date(
				System.currentTimeMillis()), "hola?", "hola", false);
	}

	@Test(expected = IllegalArgumentException.class)
	public void invalidEmailTest() {
		new User("hola", "password", "firstName", "lasName", new Date(
				System.currentTimeMillis()), "hola?", "hola", false);
	}

	@Test(expected = IllegalArgumentException.class)
	public void tooLongEmailTest() {
		new User(
				"holaholaholaholaholaholaholaholaholaholaholaholaholaholaholaholaholaholaholaholaholaholaholaholaholaholaholaholaholaholaholaholaholaholaholaholaholaholaholaholaholaholaholaholaholaholaholaholaholaholaholaholaholaholaholaholaholaholaholaholaholaholaholaholaholaholaholaholaholaholaholaholaholaholaholaholaholaholaholaholaholaholaholaholaholaholaholaholaholaholaholaholaholaholaholaholaholaholaholaholaholaholaholaholaholaholaholaholaholaholaholaholaholaholaholaholaholaholaholaholaholaholaholaholaholaholaholaholaholaholaholaholaholaholaholaholaholaholaholaholaholaholaholaholaholaholaholaholaholaholaholaholaholaholaholaholaholaholaholaholaholaholaholaholaholaholaholaholaholaholaholaholaholaholaholaholaholaholaholaholaholaholaholaholaholaholaholaholaholaholaholaholaholaholaholaholaholaholaholaholaholaholaholaholaholaholaholaholaholaholaholaholaholaholaholaholaholaholaholaholaholaholaholaholaholaholaholaholaholaholaholaholaholaholaholaholaholaholaholaholaholaholaholaholaholaholaholaholaholaholaholaholaholaholaholaholaholaholaholaholaholaholaholaholaholaholaholaholaholaholaholaholaholaholaholaholaholaholaholaholaholaholaholaholaholaholaholaholaholaholaholaholaholaholaholaholaholaholaholaholaholaholaholaholaholaholaholaholaholaholaholaholaholaholaholaholaholaholaholaholaholaholaholaholaholaholaholaholaholaholaholaholaholaholaholaholaholaholaholaholaholaholaholaholaholaholaholaholaholaholaholaholaholaholaholaholaholaholaholaholaholaholaholaholaholaholaholaholaholaholaholaholaholaholaholaholaholaholaholaholaholaholaholaholaholaholaholaholaholaholaholaholaholaholaholaholaholaholaholahola@otmail.com",
				"password", "firstName", "lasName", new Date(System
						.currentTimeMillis()), "hola?", "hola", false);
	}

	@Test(expected = IllegalArgumentException.class)
	public void tooLongQuestionTest() {
		new User(
				"hola@otmail.com",
				"password",
				"firstName",
				"lasName",
				new Date(System.currentTimeMillis()),
				"holaholaholaholaholaholaholaholaholaholaholaholaholaholaholaholaholaholaholaholaholaholaholaholaholaholaholaholaholaholaholaholaholaholaholaholaholaholaholaholaholaholaholaholaholaholaholaholaholaholaholaholaholaholaholaholaholaholaholaholaholaholaholaholaholaholaholaholaholaholaholaholaholaholaholaholaholaholaholaholaholaholaholaholaholaholaholaholaholaholaholaholaholaholaholaholaholaholaholaholaholaholaholaholaholaholaholaholaholaholaholaholaholaholaholaholaholaholaholaholaholaholaholaholaholaholaholaholaholaholaholaholaholaholaholaholaholaholaholaholaholaholaholaholaholaholaholaholaholaholaholaholaholaholaholaholaholaholaholaholaholaholaholaholaholaholaholaholaholaholaholaholaholaholaholaholaholaholaholaholaholaholaholaholaholaholaholaholaholaholaholaholaholaholaholaholaholaholaholaholaholaholaholaholaholaholaholaholaholaholaholaholaholaholaholaholaholaholaholaholaholaholaholaholaholaholaholaholaholaholaholaholaholaholaholaholaholaholaholaholaholaholaholaholaholaholaholaholaholaholaholaholaholaholaholaholaholaholaholaholaholaholaholaholaholaholaholaholaholaholaholaholaholaholaholaholaholaholaholaholaholaholaholaholaholaholaholaholaholaholaholaholaholaholaholaholaholaholaholaholaholaholaholaholaholaholaholaholaholaholaholaholaholaholaholaholaholaholaholaholaholaholaholaholaholaholaholaholaholaholaholaholaholaholaholaholaholaholaholaholaholaholaholaholaholaholaholaholaholaholaholaholaholaholaholaholaholaholaholaholaholaholaholaholaholaholaholaholaholaholaholaholaholaholaholaholaholaholaholaholaholaholaholaholaholaholaholaholaholaholaholaholaholaholaholaholaholaholaholahola?",
				"hola", false);
	}

	@Test(expected = IllegalArgumentException.class)
	public void notAQuestionTest() {
		new User("hola@otmail.com", "password", "firstName", "lasName",
				new Date(System.currentTimeMillis()), "hola!", "hola", false);
	}

	@Test(expected = IllegalArgumentException.class)
	public void tooLongAnswerTest() {
		new User(
				"hola@otmail.com",
				"password",
				"firstName",
				"lasName",
				new Date(System.currentTimeMillis()),
				"hola?",
				"holaholaholaholaholaholaholaholaholaholaholaholaholaholaholaholaholaholaholaholaholaholaholaholaholaholaholaholaholaholaholaholaholaholaholaholaholaholaholaholaholaholaholaholaholaholaholaholaholaholaholaholaholaholaholaholaholaholaholaholaholaholaholaholaholaholaholaholaholaholaholaholaholaholaholaholaholaholaholaholaholaholaholaholaholaholaholaholaholaholaholaholaholaholaholaholaholaholaholaholaholaholaholaholaholaholaholaholaholaholaholaholaholaholaholaholaholaholaholaholaholaholaholaholaholaholaholaholaholaholaholaholaholaholaholaholaholaholaholaholaholaholaholaholaholaholaholaholaholaholaholaholaholaholaholaholaholaholaholaholaholaholaholaholaholaholaholaholaholaholaholaholaholaholaholaholaholaholaholaholaholaholaholaholaholaholaholaholaholaholaholaholaholaholaholaholaholaholaholaholaholaholaholaholaholaholaholaholaholaholaholaholaholaholaholaholaholaholaholaholaholaholaholaholaholaholaholaholaholaholaholaholaholaholaholaholaholaholaholaholaholaholaholaholaholaholaholaholaholaholaholaholaholaholaholaholaholaholaholaholaholaholaholaholaholaholaholaholaholaholaholaholaholaholaholaholaholaholaholaholaholaholaholaholaholaholaholaholaholaholaholaholaholaholaholaholaholaholaholaholaholaholaholaholaholaholaholaholaholaholaholaholaholaholaholaholaholaholaholaholaholaholaholaholaholaholaholaholaholaholaholaholaholaholaholaholaholaholaholaholaholaholaholaholaholaholaholaholaholaholaholaholaholaholaholaholaholaholaholaholaholaholaholaholaholaholaholaholaholaholaholaholaholaholaholaholaholaholaholaholaholaholaholaholaholaholaholaholaholaholaholaholaholaholaholaholaholaholaholahola",
				false);
	}

	@Test
	public void canCommentTest() {
		User user = new User("hola@otmail.com", "password", "firstName",
				"lasName", new Date(System.currentTimeMillis()), "hola?",
				"hola", false);
		Movie movie = new Movie();
		movie.setReleaseDate(new Date(System.currentTimeMillis() + 1000000));
		Assert.assertFalse(user.canComment(movie));
		movie.setReleaseDate(new Date(System.currentTimeMillis() - 100000));
		Assert.assertTrue(user.canComment(movie));
		movie.setReleaseDate(new Date(System.currentTimeMillis() + 1000000));
		User vip = new User("hola@hotmail.com", "password", "firstName",
				"lasName", new Date(System.currentTimeMillis()), "hola?",
				"hola", true);
		Assert.assertTrue(vip.canComment(movie));
	}
	
	@Test
	public void okTest() {
		new User(
				"email@email.com",
				"password",
				"frstName",
				"lastName", new Date(System.currentTimeMillis()), "hola?",
				"hola", false);
	}
}