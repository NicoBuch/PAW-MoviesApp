package ar.edu.itba.it.paw.domain;

import java.sql.Date;

import org.junit.Assert;
import org.junit.Test;

import ar.edu.itba.it.paw.domain.comment.Comment;
import ar.edu.itba.it.paw.domain.movie.Movie;
import ar.edu.itba.it.paw.domain.user.User;

public class CommentTest {

	@Test(expected = IllegalArgumentException.class)
	public void illegalRatingTest() {
		new Comment("body", 6, new Movie(), new User());
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void illegalRatingTest2() {
		new Comment("body", -1 , new Movie(), new User());
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void nullMovieTest() {
		new Comment("body", 4, null, new User());
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void nullUserTest() {
		new Comment("body", 4, new Movie(),null);
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void nullBodyTest() {
		new Comment(null, 4, new Movie(),new User());
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void emptyBodyTest() {
		new Comment("", 4, new Movie(),new User());
	}
	
	@Test
	public void okTest() {
		new Comment("body", 4, new Movie(),new User());
	}
	
	@Test
	public void compareTest() {
		User user = new User(
				"email@email.com",
				"password",
				"frstName",
				"lastName", new Date(System.currentTimeMillis()), "hola?",
				"hola", false);
		Comment comment = new Comment("body", 4, new Movie(),user);
		comment.rate(new User(), 3);
		comment.rate(new User(), 4);
		comment.rate(new User(), 5);
		Comment comment2 = new Comment("body", 4, new Movie(),user);;
		comment2.rate(new User(), 1);
		comment2.rate(new User(), 2);
		comment2.rate(new User(), 3);
		Assert.assertTrue(comment.compareTo(comment2) < 0);
	}
}
