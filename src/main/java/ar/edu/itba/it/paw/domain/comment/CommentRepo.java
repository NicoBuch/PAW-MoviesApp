package ar.edu.itba.it.paw.domain.comment;

public interface CommentRepo{
	public void delete(Comment comment);
	public Comment get(int id) throws NoCommentIdException;
	public void save(Comment comment);
}
