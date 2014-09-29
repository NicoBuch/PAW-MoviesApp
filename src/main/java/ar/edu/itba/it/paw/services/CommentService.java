package ar.edu.itba.it.paw.services;

import ar.edu.itba.it.paw.exceptions.CantCommentBeforeMoviesReleaseDateException;
import ar.edu.itba.it.paw.exceptions.NoMoreThanOneCommentPerUserPerMovieException;
import ar.edu.itba.it.paw.models.Comment;
import ar.edu.itba.it.paw.models.Movie;
import ar.edu.itba.it.paw.models.MovieWithComments;
import ar.edu.itba.it.paw.models.User;

public interface CommentService {

	public void save(Comment c)
			throws NoMoreThanOneCommentPerUserPerMovieException,
			CantCommentBeforeMoviesReleaseDateException;

	public Iterable<Comment> getCommentsByMovie(Movie m);

	public Iterable<Comment> getCommentsByUser(User u);
	

	public int countCommentsByMovie(Movie m);

	public boolean alreadyComment(User u, Movie movie);
	
	public boolean canComment(User u, Movie movie);

	public MovieWithComments getMovieWithComments(Movie movie);
}
