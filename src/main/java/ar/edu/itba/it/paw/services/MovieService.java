package ar.edu.itba.it.paw.services;

import java.sql.Date;

import ar.edu.itba.it.paw.exceptions.NoGenreException;
import ar.edu.itba.it.paw.exceptions.NoMovieIdException;
import ar.edu.itba.it.paw.models.Movie;

public interface MovieService {
	public Iterable<Movie> getAll();

	public Movie getById(long id) throws NoMovieIdException;

	public void save(Movie movie);

	public boolean isNew(Movie movie);
	
	public Iterable<Movie> getByGenre(String genre) throws NoGenreException;
	
	public Iterable<Movie> getByRating(int limit);
	
	public Iterable<Movie> getByCreationDate(int limit);
	
	public Iterable<Movie> getByReleaseDate(Date from, Date to);
	
	public boolean alreadyRelease(Movie movie);
	
	public Iterable<Movie> getByDirector(String director);


}
