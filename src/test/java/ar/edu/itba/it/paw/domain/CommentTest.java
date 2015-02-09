package ar.edu.itba.it.paw.domain;

import java.sql.Date;
import java.util.HashSet;
import java.util.Set;

import org.junit.Assert;
import org.junit.Test;

import ar.edu.itba.it.paw.domain.comment.Comment;
import ar.edu.itba.it.paw.domain.genre.Genre;
import ar.edu.itba.it.paw.domain.movie.Movie;
import ar.edu.itba.it.paw.domain.user.User;

public class CommentTest {
	User randomUser = new User(
			"email@email.com",
			"password",
			"frstName",
			"lastName", new Date(System.currentTimeMillis()), "hola?",
			"hola", false);
	Set<Genre> genres = new HashSet<Genre>();
	Movie randomMovie; 
	
	public CommentTest() {
		genres.add(new Genre("andy"));
		randomMovie = new Movie("title", new Date(System.currentTimeMillis()), "director",genres, 15, "caca");
	}
	@Test(expected = IllegalArgumentException.class)
	public void illegalRatingTest() {
		new Comment("body", 6, randomMovie, randomUser);
	}

	@Test(expected = IllegalArgumentException.class)
	public void illegalRatingTest2() {
		new Comment("body", -1 , randomMovie, randomUser);
	}

	@Test(expected = IllegalArgumentException.class)
	public void nullMovieTest() {
		new Comment("body", 4, null, randomUser);
	}

	@Test(expected = IllegalArgumentException.class)
	public void nullUserTest() {
		new Comment("body", 4, randomMovie,null);
	}

	@Test(expected = IllegalArgumentException.class)
	public void nullBodyTest() {
		new Comment(null, 4, randomMovie,randomUser);
	}

	@Test(expected = IllegalArgumentException.class)
	public void emptyBodyTest() {
		new Comment("", 4, randomMovie,randomUser);
	}

	@Test
	public void okTest() {
		new Comment("body", 4, randomMovie,randomUser);
	}

	@Test
	public void compareTest() {
		User user = new User(
				"email2@email.com",
				"password",
				"frstName",
				"lastName", new Date(System.currentTimeMillis()), "hola?",
				"hola", false);
		Comment comment = new Comment("body", 4, randomMovie,user);
		comment.rate(randomUser, 3);
		comment.rate(randomUser, 4);
		comment.rate(randomUser, 5);
		Comment comment2 = new Comment("body", 4, randomMovie,user);;
		comment2.rate(randomUser, 1);
		comment2.rate(randomUser, 2);
		comment2.rate(randomUser, 3);
		Assert.assertTrue(comment.compareTo(comment2) < 0);
	}
}
