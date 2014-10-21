package ar.edu.itba.it.paw.daos;

import java.sql.Date;
import java.util.List;

import org.hibernate.Query;
import org.hibernate.SessionFactory;
import org.hibernate.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import ar.edu.itba.it.paw.models.Movie;

@Repository
public class HibernateMovieDao extends HibernateGenericDao<Movie> implements
		MovieDao {

	@Autowired
	public HibernateMovieDao(SessionFactory sessionFactory) {
		super.setSessionFactory(sessionFactory);
	}

	@SuppressWarnings("unchecked")
	public Iterable<Movie> getByGenre(String genre) {
		Session session = getSession();
		Query query = session.createQuery(" from Movie where genre = ?");
		query.setParameter(0, genre);
		List<Movie> result = (List<Movie>) query.list();
		return result.size() > 0 ? result : null;
	}

	@SuppressWarnings("unchecked")
	public Iterable<Movie> getByRating(int limit) {
		Session session = getSession();
		Query query = session
				.createQuery("select comment.movie from Comment comment group by comment.movie"
						   + " order by avg(rating) desc limit ?");

		query.setParameter(0, limit);
		List<Movie> result = (List<Movie>) query.list();
		return result.size() > 0 ? result : null;
	}

	public Iterable<Movie> getByCreationDate(int limit) {
		// TODO Auto-generated method stub
		return null;
	}

	public Iterable<Movie> getByReleaseDate(Date from, Date to) {
		// TODO Auto-generated method stub
		return null;
	}

	public Iterable<Movie> getByDirector(String director) {
		// TODO Auto-generated method stub
		return null;
	}

}
