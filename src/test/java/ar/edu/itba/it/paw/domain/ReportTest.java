package ar.edu.itba.it.paw.domain;

import java.sql.Date;

import org.junit.Test;

import ar.edu.itba.it.paw.domain.comment.Comment;
import ar.edu.itba.it.paw.domain.movie.Movie;
import ar.edu.itba.it.paw.domain.report.Report;
import ar.edu.itba.it.paw.domain.user.User;

public class ReportTest {

	@Test(expected = IllegalArgumentException.class)
	public void cantReportTest() {
		User user = new User("email@email.com", "password", "frstName",
				"lastName", new Date(System.currentTimeMillis()), "hola?",
				"hola", false);
		Comment comment = new Comment("body", 4, new Movie(), user);
		user.report(new Report(user, comment));
		new Report(user, comment);
	}
	
	@Test
	public void okTest() {
		User user = new User("email@email.com", "password", "frstName",
				"lastName", new Date(System.currentTimeMillis()), "hola?",
				"hola", false);
		Comment comment = new Comment("body", 4, new Movie(), user);
		user.report(new Report(user, comment));
	}
}
