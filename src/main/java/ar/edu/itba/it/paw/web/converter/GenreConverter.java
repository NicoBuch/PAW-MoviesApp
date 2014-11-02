package ar.edu.itba.it.paw.web.converter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import ar.edu.itba.it.paw.domain.NoIdException;
import ar.edu.itba.it.paw.domain.genre.Genre;
import ar.edu.itba.it.paw.domain.genre.GenreRepo;
@Component
public class GenreConverter implements Converter<String,Genre>{
	private GenreRepo  genres;
	@Autowired
	public GenreConverter(GenreRepo genres){
		this.genres = genres;
	}
	public Genre convert(String source) {
		try {
			return (genres.get(Integer.valueOf(source)));
		} catch (NoIdException e) {
			throw new RuntimeException();
		}
	}

}