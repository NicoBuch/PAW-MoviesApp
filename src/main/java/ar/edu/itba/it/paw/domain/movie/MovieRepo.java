package ar.edu.itba.it.paw.domain.movie;

import java.sql.Date;
import java.util.List;


public interface MovieRepo {

	public List<Movie> getAll();
	
	public Movie get(int studentId) throws NoMovieIdException;
	
	public List<Movie> getByGenre(String genre)  throws NoGenreException;
	
	public List<Movie> getByDirector(String director);
	
	public List<Movie> getByRating(int limit);
	
	public List<Movie> getByReleaseDate(Date from, Date to);
	
	public List<Movie> getByCreationDate(int limit);
	
	
	
}
