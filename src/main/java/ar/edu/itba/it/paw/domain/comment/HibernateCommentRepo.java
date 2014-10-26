package ar.edu.itba.it.paw.domain.comment;

import org.hibernate.HibernateException;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import ar.edu.itba.it.paw.domain.AbstractHibernateRepo;

@Repository
public class HibernateCommentRepo extends AbstractHibernateRepo implements CommentRepo{
	
	@Autowired
	public HibernateCommentRepo(SessionFactory sessionFactory) {
		super(sessionFactory);
	}

	public void delete(Comment comment){
		super.delete(comment);
	}
	
	public Comment get(int id) throws NoCommentIdException{
		try {
			return get(Comment.class, id);
		} catch (HibernateException e) {
			throw new NoCommentIdException();
		}

	}
	public void save(Comment comment){
		super.save(comment);
	}
}
