package ar.edu.itba.it.paw.services;

import ar.edu.itba.it.paw.models.Movie;

public class MovieServiceImpl {
	private static MovieServiceImpl obj = null;

	private MovieServiceImpl() {

	}

	public static MovieServiceImpl getInstance() {
		if (obj == null)
			obj = new MovieServiceImpl();
		return obj;
	}

	/* Devuelve listado de peliculas */
	public Iterable<Movie> getAll() {
		return null;
	}

	/*
	 * Devuelve una pelicula, se puede cambiar el tipo long por el tipo que
	 * tenga entity
	 */
	public Movie getById(long id) {
		return null;
	}

	/* Actualiza una pelicula, la crea si no estaba creada */
	public void save(Movie movie) {

	}

	/* Se fija si es estreno la pelicula */
	public boolean isNew(Movie movie) {
		return false;
	}
}
