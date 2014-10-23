package ar.edu.itba.it.paw.daos;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import ar.edu.itba.it.paw.models.Comment;
import ar.edu.itba.it.paw.models.Movie;
import ar.edu.itba.it.paw.models.User;

@Repository
public class HibernateCommentDao extends HibernateGenericDao<Comment> implements CommentDao{

	@Autowired
	public HibernateCommentDao(SessionFactory sessionFactory) {
		super.setSessionFactory(sessionFactory);
	}
	
	@SuppressWarnings("unchecked")
	public Comment getCommentsByUserAndMovie(User u, Movie m) {
		Session session = getSession();
		Query query = session.createQuery("from Comment where movie = ? and user = ?");
		query.setParameter(0, m);
		query.setParameter(1, u);
		List<Comment> result = (List<Comment>)query.list();
		return result.size() > 0 ? result.get(0) : null;
	}

	public Iterable<Comment> getCommentsByMovie(Movie m) {
		// TODO Auto-generated method stub
		return null;
	}

	public Iterable<Comment> getCommentsByUser(User u) {
		// TODO Auto-generated method stub
		return null;
	}

	public int countCommentsByMovie(Movie m) {
		// TODO Auto-generated method stub
		return 0;
	}
}
