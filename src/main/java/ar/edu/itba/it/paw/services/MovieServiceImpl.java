package ar.edu.itba.it.paw.services;

import java.util.Date;

import ar.edu.itba.it.paw.daos.MovieDao;
import ar.edu.itba.it.paw.daos.PostgresMovieDao;
import ar.edu.itba.it.paw.exceptions.NoGenreException;
import ar.edu.itba.it.paw.exceptions.NoMovieIdException;
import ar.edu.itba.it.paw.models.Movie;

public class MovieServiceImpl implements MovieService {
	MovieDao movieDao;
	private static MovieServiceImpl obj = null;

	private MovieServiceImpl() {
		movieDao = PostgresMovieDao.getInstance();
	}

	public static MovieServiceImpl getInstance() {
		if (obj == null)
			obj = new MovieServiceImpl();
		return obj;
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
	
}
