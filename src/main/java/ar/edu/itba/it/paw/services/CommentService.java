package ar.edu.itba.it.paw.services;

import ar.edu.itba.it.paw.daos.CommentDao;
import ar.edu.itba.it.paw.daos.PostgresCommentDao;
import ar.edu.itba.it.paw.exceptions.CantCommentBeforeMoviesReleaseDate;
import ar.edu.itba.it.paw.exceptions.NoMoreThanOneCommentPerUserPerMovieException;
import ar.edu.itba.it.paw.models.Comment;
import ar.edu.itba.it.paw.models.Movie;
import ar.edu.itba.it.paw.models.User;

public class CommentService {
	private static CommentService service;
	private CommentDao dao;

	private CommentService() {
		dao = PostgresCommentDao.getInstance();
	}

	public static CommentService getInstance() {
		if (service == null) {
			service = new CommentService();
		}
		return service;
	}

	public void update(Comment c)
			throws NoMoreThanOneCommentPerUserPerMovieException,
			CantCommentBeforeMoviesReleaseDate {
		if (c.getMovie().getReleaseDate().after(c.getCreationDate())) {
			throw new CantCommentBeforeMoviesReleaseDate();
		}
		Comment usersComment = dao.getCommentsByUserAndMovie(c.getUser(),
				c.getMovie());
		if (usersComment != null) {
			throw new NoMoreThanOneCommentPerUserPerMovieException();
		}

		dao.update(c);
	}

	public Iterable<Comment> getCommentsByMovie(Movie m) {
		return dao.getCommentsByMovie(m);
	}

	public Iterable<Comment> getCommentsByUser(User u) {
		return dao.getCommentsByUser(u);
	}

}
