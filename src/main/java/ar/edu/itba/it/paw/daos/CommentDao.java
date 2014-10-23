package ar.edu.itba.it.paw.daos;

import ar.edu.itba.it.paw.models.Comment;
import ar.edu.itba.it.paw.models.Movie;
import ar.edu.itba.it.paw.models.User;

public interface CommentDao {
	public abstract Iterable<Comment> findAll();

	public abstract Comment find(long id);

	public abstract Comment store(Comment comment);

	public abstract Comment getCommentsByUserAndMovie(User u, Movie m);

	public abstract Iterable<Comment> getCommentsByMovie(Movie m);

	public abstract Iterable<Comment> getCommentsByUser(User u);
	
	public abstract int countCommentsByMovie(Movie m);

}
