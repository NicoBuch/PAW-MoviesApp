package ar.edu.itba.it.paw.domain;

import java.sql.Date;
import java.util.HashSet;
import java.util.Set;

import org.junit.Assert;
import org.junit.Test;

import ar.edu.itba.it.paw.domain.comment.Comment;
import ar.edu.itba.it.paw.domain.genre.Genre;
import ar.edu.itba.it.paw.domain.movie.Movie;

public class MovieTest {

	@Test(expected = IllegalArgumentException.class)
	public void emptyTitleTest() {
		Set<Genre> genres = new HashSet<Genre>();
		genres.add(new Genre("andy"));
		new Movie("", new Date(System.currentTimeMillis()), "director", genres,
				15, "description");
	}

	@Test(expected = IllegalArgumentException.class)
	public void emptyReleaseDateTest() {
		Set<Genre> genres = new HashSet<Genre>();
		genres.add(new Genre("andy"));
		new Movie("title", null, "director", genres, 15, "description");
	}

	@Test(expected = IllegalArgumentException.class)
	public void emptyDirectorTest() {
		Set<Genre> genres = new HashSet<Genre>();
		genres.add(new Genre("andy"));
		new Movie("title", new Date(System.currentTimeMillis()), "", genres,
				15, "caca");
	}

	@Test(expected = IllegalArgumentException.class)
	public void emptyGenresTest() {
		Set<Genre> genres = new HashSet<Genre>();
		new Movie("title", new Date(System.currentTimeMillis()), "director",
				genres, 15, "caca");
	}

	@Test(expected = IllegalArgumentException.class)
	public void emptyDescriptionTest() {
		Set<Genre> genres = new HashSet<Genre>();
		genres.add(new Genre("andy"));
		new Movie("title", new Date(System.currentTimeMillis()), "director",
				genres, 15, "");
	}

	@Test(expected = IllegalArgumentException.class)
	public void titleTooLongTest() {
		Set<Genre> genres = new HashSet<Genre>();
		genres.add(new Genre("andy"));
		String a = "11111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111";
		new Movie(a, new Date(System.currentTimeMillis()), "director", genres,
				15, "caca");
	}

	@Test(expected = IllegalArgumentException.class)
	public void directorTooLongTest() {
		Set<Genre> genres = new HashSet<Genre>();
		genres.add(new Genre("andy"));
		String a = "11111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111";
		new Movie("title", new Date(System.currentTimeMillis()), a, genres, 15,
				"caca");
	}

	@Test(expected = IllegalArgumentException.class)
	public void nullGenresTest() {
		new Movie("title", new Date(System.currentTimeMillis()), "director",
				null, 15, "caca");
	}

	@Test
	public void OkTest() {
		Set<Genre> genres = new HashSet<Genre>();
		genres.add(new Genre("andy"));
		new Movie("title", new Date(System.currentTimeMillis()), "director",
				genres, 15, "caca");
	}

	@Test
	public void isNewTest() {
		Set<Genre> genres = new HashSet<Genre>();
		genres.add(new Genre("andy"));
		Movie movie = new Movie("title", new Date(System.currentTimeMillis()),
				"director", genres, 15, "caca");

		Assert.assertTrue(movie.isNew());
		movie.setReleaseDate(Date.valueOf("2014-10-9"));
		Assert.assertFalse(movie.isNew());
	}

	@Test
	public void addCommentTest() {
		Set<Genre> genres = new HashSet<Genre>();
		genres.add(new Genre("andy"));
		Movie movie = new Movie("title", new Date(System.currentTimeMillis()),
				"director", genres, 15, "caca");
		Comment comment = new Comment();
		Assert.assertEquals(movie.getComments().size(), 0);
		movie.addComment(comment);
		Assert.assertEquals(movie.getComments().size(), 1);
		Assert.assertTrue(comment.getMovie() == movie);
	}

	@Test
	public void alReadyReleasedtTest() {
		Set<Genre> genres = new HashSet<Genre>();
		genres.add(new Genre("andy"));
		Movie movie = new Movie("title", new Date(
				System.currentTimeMillis() - 10000), "director", genres, 15,
				"caca");
		Assert.assertTrue(movie.alreadyReleased());
		movie.setReleaseDate(new Date(System.currentTimeMillis() + 10000));
		Assert.assertFalse(movie.alreadyReleased());
	}

	@Test
	public void setGenresTest() {
		Set<Genre> genres = new HashSet<Genre>();
		Set<Genre> genresInit = new HashSet<Genre>();
		Genre genre1 = new Genre("andy");
		Genre genre2 = new Genre("juan");
		Genre genre3 = new Genre("mati");
		genresInit.add(genre1);
		Movie movie = new Movie("title", new Date(
				System.currentTimeMillis() - 10000), "director", genresInit,
				15, "caca");
		genres.add(genre2);
		movie.setGenres(genres);
		Assert.assertTrue(movie.getGenres().contains(genre2)
				&& !movie.getGenres().contains(genre3));
		genres.remove(genre2);
		genres.add(genre3);
		movie.setGenres(genres);
		Assert.assertTrue(movie.getGenres().contains(genre3)
				&& !movie.getGenres().contains(genre2));
	}

}
