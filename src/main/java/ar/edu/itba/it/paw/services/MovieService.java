package ar.edu.itba.it.paw.services;

import ar.edu.itba.it.paw.models.Movie;

public interface MovieService {
	public Iterable<Movie> getAll();

	public Movie getById(long id);

	public void save(Movie movie);

	public boolean isNew(Movie movie);
	
	public Movie getByGenre(String genre);

}
