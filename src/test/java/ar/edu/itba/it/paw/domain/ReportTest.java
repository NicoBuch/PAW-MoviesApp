package ar.edu.itba.it.paw.domain;

import java.sql.Date;
import java.util.HashSet;
import java.util.Set;

import org.junit.Test;

import ar.edu.itba.it.paw.domain.comment.Comment;
import ar.edu.itba.it.paw.domain.genre.Genre;
import ar.edu.itba.it.paw.domain.movie.Movie;
import ar.edu.itba.it.paw.domain.report.Report;
import ar.edu.itba.it.paw.domain.user.User;

public class ReportTest {

	Movie randomMovie;
	Set<Genre> genres = new HashSet<Genre>();

	public ReportTest() {
		genres.add(new Genre("andy"));
		randomMovie = new Movie("title", new Date(System.currentTimeMillis()), "director",genres, 15, "caca");
	}

	@Test(expected = IllegalArgumentException.class)
	public void cantReportTest() {
		User user = new User("email@email.com", "password", "frstName",
				"lastName", new Date(System.currentTimeMillis()), "hola?",
				"hola", false);
		Comment comment = new Comment("body", 4, randomMovie, user);
		user.report(new Report(user, comment));
		new Report(user, comment);
	}

	@Test
	public void okTest() {
		User user = new User("email@email.com", "password", "frstName",
				"lastName", new Date(System.currentTimeMillis()), "hola?",
				"hola", false);
		Comment comment = new Comment("body", 4, randomMovie, user);
		user.report(new Report(user, comment));
	}
}
