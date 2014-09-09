package ar.edu.itba.it.paw.daos;

import ar.edu.itba.it.paw.models.Movie;

public interface MovieDao {
	public abstract Iterable<Movie> getAll();
	public abstract Movie getById(long id);
	public abstract void update(Movie movie);
	public abstract Movie getByGenre(String genre);
}
