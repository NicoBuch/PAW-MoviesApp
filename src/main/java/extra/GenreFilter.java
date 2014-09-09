package extra;

import ar.edu.itba.it.paw.models.Movie;
import ar.edu.itba.it.paw.models.Movie.Genre;

public class GenreFilter implements Filter{
	
	Genre genre;
	
	public GenreFilter(Genre genre){
		this.genre = genre;
	}

	public boolean evaluate(Movie movie) {
		if(movie.getGenre().equals(genre))
			return true;
		return false;
	}

}
