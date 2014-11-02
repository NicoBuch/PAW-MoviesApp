package ar.edu.itba.it.paw.domain.comment;

import java.util.List;

import ar.edu.itba.it.paw.domain.NoIdException;
import ar.edu.itba.it.paw.domain.commentRating.CommentRating;
import ar.edu.itba.it.paw.domain.movie.Movie;

public interface CommentRepo{
	public void delete(Comment comment);
	public Comment get(int id) throws NoIdException;
	public void save(Comment comment);
	public List<Comment> getByMovie(Movie movie);
	public void rate(CommentRating commentRating);
}
