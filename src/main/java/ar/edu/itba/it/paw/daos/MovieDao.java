package ar.edu.itba.it.paw.daos;

import java.sql.Date;

import ar.edu.itba.it.paw.models.Movie;

public interface MovieDao {
	public  Iterable<Movie> getAll();
	public  Movie getById(long id);
	public  void save(Movie movie);
	public  Iterable<Movie> getByGenre(String genre);
	public  Iterable<Movie> getByRating(int limit);
	public  Iterable<Movie> getByCreationDate(int limit);
	public  Iterable<Movie> getByReleaseDate(Date from, Date to);
	public	Iterable<Movie> getByDirector(String director);
}
