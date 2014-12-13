package ar.edu.itba.it.paw.web.converter;

import java.util.Locale;

import org.apache.wicket.util.convert.IConverter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import ar.edu.itba.it.paw.domain.NoIdException;
import ar.edu.itba.it.paw.domain.genre.Genre;
import ar.edu.itba.it.paw.domain.genre.GenreRepo;
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