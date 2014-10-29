package ar.edu.itba.it.paw.domain.comment;

import ar.edu.itba.it.paw.domain.NoIdException;

public interface CommentRepo{
	public void delete(Comment comment);
	public Comment get(int id) throws NoIdException;
	public void save(Comment comment);
}
