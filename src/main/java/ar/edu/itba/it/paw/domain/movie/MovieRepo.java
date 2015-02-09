package ar.edu.itba.it.paw.domain.movie;

import java.io.FileNotFoundException;
import java.sql.Date;
import java.util.List;

import ar.edu.itba.it.paw.domain.NoIdException;
import ar.edu.itba.it.paw.domain.genre.Genre;


public interface MovieRepo {

	public List<Movie> getAll();
	
	public Movie get(int studentId) throws NoIdException;
	
	public List<Movie> getByGenre(Genre genre);
	
	public List<Movie> getByDirector(String director);
	
	public List<Movie> getByRating(int limit);
	
	public List<Movie> getByReleaseDate(Date from, Date to);
	
	public List<Movie> getByCreationDate(int limit);
	
	public void save(Movie movie);
	
	public void delete(Movie movie);

	public List<Movie> getByVisits(int limit);

	int getStock(Movie movie) throws NoStockAvailableException, FileNotFoundException;

}
