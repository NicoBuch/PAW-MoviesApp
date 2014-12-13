package ar.edu.itba.it.paw.domain.genre;

import java.util.List;

import ar.edu.itba.it.paw.domain.NoIdException;

public interface GenreRepo {
	public List<Genre> getAll();
	
	public Genre get(int genreId) throws NoIdException;

	public Genre getByName(String genre);
}
