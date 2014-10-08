package ar.edu.itba.it.paw.web.converter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import ar.edu.itba.it.paw.exceptions.NoMovieIdException;
import ar.edu.itba.it.paw.models.Movie;
import ar.edu.itba.it.paw.services.MovieService;
@Component
public class MovieConverter implements Converter<String,Movie>{
	private MovieService  movieService;
	@Autowired
	public MovieConverter(MovieService movieService){
		this.movieService = movieService;
	}
	public Movie convert(String source) {
		try {
			return (movieService.getById(Integer.valueOf(source)));
		} catch (NoMovieIdException e) {
			throw new RuntimeException();
		}
	}

}
