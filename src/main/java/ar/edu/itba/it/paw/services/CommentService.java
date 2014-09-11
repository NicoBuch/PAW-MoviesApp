package ar.edu.itba.it.paw.services;

import ar.edu.itba.it.paw.exceptions.CantCommentBeforeMoviesReleaseDateException;
import ar.edu.itba.it.paw.exceptions.NoMoreThanOneCommentPerUserPerMovieException;
import ar.edu.itba.it.paw.models.Comment;
import ar.edu.itba.it.paw.models.Movie;
import ar.edu.itba.it.paw.models.User;

public interface CommentService {

	public void save(Comment c)
			throws NoMoreThanOneCommentPerUserPerMovieException,
			CantCommentBeforeMoviesReleaseDateException;

	public Iterable<Comment> getCommentsByMovie(Movie m);

	public Iterable<Comment> getCommentsByUser(User u);
}
