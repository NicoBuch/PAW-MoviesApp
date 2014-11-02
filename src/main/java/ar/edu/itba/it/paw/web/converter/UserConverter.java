package ar.edu.itba.it.paw.web.converter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import ar.edu.itba.it.paw.domain.NoIdException;
import ar.edu.itba.it.paw.domain.user.User;
import ar.edu.itba.it.paw.domain.user.UserRepo;

@Component
public class UserConverter implements Converter<String,User> {
	UserRepo users;
	
	@Autowired
	public UserConverter(UserRepo users){
		this.users = users;
	}
	
	@Override
	public User convert(String source) {
		try {
			return (users.get(Integer.valueOf(source)));
		} catch (NoIdException e) {
			throw new RuntimeException();
		}
	}

}
