package ar.edu.itba.it.paw.web.converter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import ar.edu.itba.it.paw.domain.NoIdException;
import ar.edu.itba.it.paw.domain.comment.Comment;
import ar.edu.itba.it.paw.domain.comment.CommentRepo;
@Component
public class CommentConverter implements Converter<String,Comment>{
	private CommentRepo comments;
	
	@Autowired
	public CommentConverter(CommentRepo comments){
		this.comments = comments;
	}
	public Comment convert(String source) {
		try {
			return (comments.get(Integer.valueOf(source)));
		} catch (NoIdException e) {
			throw new RuntimeException();
		}
	}

}