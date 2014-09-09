package extra;

import ar.edu.itba.it.paw.models.Movie;

public interface Filter {
	public boolean evaluate(Movie movie);
}
