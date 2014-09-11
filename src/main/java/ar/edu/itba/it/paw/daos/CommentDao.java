package ar.edu.itba.it.paw.daos;

import ar.edu.itba.it.paw.models.Comment;
import ar.edu.itba.it.paw.models.Movie;
import ar.edu.itba.it.paw.models.User;

public interface CommentDao {
	public abstract Iterable<Comment> getAll();

	public abstract Comment getById(long id);

	public abstract void save(Comment comment);

	public abstract Comment getCommentsByUserAndMovie(User u, Movie m);

	public abstract Iterable<Comment> getCommentsByMovie(Movie m);

	public abstract Iterable<Comment> getCommentsByUser(User u);

}
