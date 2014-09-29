package ar.edu.itba.it.paw.services;

import java.sql.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ar.edu.itba.it.paw.daos.MovieDao;
import ar.edu.itba.it.paw.exceptions.NoGenreException;
import ar.edu.itba.it.paw.exceptions.NoMovieIdException;
import ar.edu.itba.it.paw.models.Movie;
@Service
public class MovieServiceImpl implements MovieService {
	MovieDao movieDao;
	@Autowired
	public MovieServiceImpl(MovieDao movieDao) {
		this.movieDao = movieDao;
	}

	/* Devuelve listado de peliculas */
	public Iterable<Movie> getAll() {
		Iterable<Movie> movies = movieDao.getAll();
		return movies;
	}

	/*
	 * Devuelve una pelicula, se puede cambiar el tipo long por el tipo que
	 * tenga entity
	 */
	public Movie getById(long id) throws NoMovieIdException{
		Movie movie = movieDao.getById(id);
		if(movie == null){
			throw new NoMovieIdException();
		}
		return movie;
	}

	/* Actualiza una pelicula, la crea si no estaba creada */
	public void save(Movie movie) {
		movieDao.save(movie);
	}

	/* Se fija si es estreno la pelicula */
	public boolean isNew(Movie movie) {
		if( movie.getReleaseDate().after(new Date(System.currentTimeMillis() - 604800000))){
			return true;
		}
		return false;
	}

	public Iterable<Movie> getByGenre(String genre) throws NoGenreException 
	{
		try{
			Movie.Genre.valueOf(genre.toUpperCase());
			Iterable<Movie> movies = movieDao.getByGenre(genre.toUpperCase());
			return movies;	
		}catch (IllegalArgumentException e){
			throw new NoGenreException();
		}
	}
	public boolean alreadyRelease(Movie movie){
		if(movie.getReleaseDate().compareTo(new Date(System.currentTimeMillis())) < 0){
			return true;
		}
		return false;
		
	}
	
	
	public Iterable<Movie> getByRating(int limit){
		return movieDao.getByRating(limit);
	}

	public Iterable<Movie> getByReleaseDate(Date from, Date to) {
		return movieDao.getByReleaseDate(from, to);
	}

	public Iterable<Movie> getByCreationDate(int limit) {
		return movieDao.getByCreationDate(limit);
	}

	public Iterable<Movie> getByDirector(String director) {
		return movieDao.getByDirector(director);
	}
	
	
	
}
