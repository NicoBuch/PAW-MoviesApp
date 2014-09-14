package ar.edu.itba.it.paw.daos;

import java.sql.Date;

import ar.edu.itba.it.paw.models.Movie;

public interface MovieDao {
	public abstract Iterable<Movie> getAll();
	public abstract Movie getById(long id);
	public abstract void save(Movie movie);
	public abstract Iterable<Movie> getByGenre(String genre);
	public abstract Iterable<Movie> getByRating(int limit);
	public abstract Iterable<Movie> getByCreationDate(int limit);
	public abstract Iterable<Movie> getByReleaseDate(Date from, Date to);
}
