package ar.edu.itba.it.paw.web.converter;

import java.util.Locale;

import org.apache.wicket.util.convert.IConverter;
import org.springframework.stereotype.Component;

import ar.edu.itba.it.paw.domain.genre.Genre;
@SuppressWarnings("serial")
@Component
public class GenreConverter implements IConverter<Genre>{

	@Override
	public Genre convertToObject(String arg0, Locale arg1) {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public String convertToString(Genre arg0, Locale arg1) {
		// TODO Auto-generated method stub
		return null;
	}

}