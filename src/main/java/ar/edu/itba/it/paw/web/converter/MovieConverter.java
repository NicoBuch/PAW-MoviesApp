package ar.edu.itba.it.paw.web.converter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import ar.edu.itba.it.paw.domain.NoIdException;
import ar.edu.itba.it.paw.domain.movie.Movie;
import ar.edu.itba.it.paw.domain.movie.MovieRepo;
@Component
public class MovieConverter implements Converter<String,Movie>{
	private MovieRepo  movies;
	@Autowired
	public MovieConverter(MovieRepo movies){
		this.movies = movies;
	}
	public Movie convert(String source) {
		try {
			return (movies.get(Integer.valueOf(source)));
		} catch (NoIdException e) {
			throw new RuntimeException();
		}
	}

}
