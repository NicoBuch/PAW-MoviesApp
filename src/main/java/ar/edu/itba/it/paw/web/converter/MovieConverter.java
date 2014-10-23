package ar.edu.itba.it.paw.web.converter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import ar.edu.itba.it.paw.domain.MovieRepo;
import ar.edu.itba.it.paw.exceptions.NoMovieIdException;
import ar.edu.itba.it.paw.models.Movie;
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
		} catch (NoMovieIdException e) {
			throw new RuntimeException();
		}
	}

}
