package ar.edu.itba.it.paw.domain.movie;

import java.sql.Date;
import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.SessionFactory;
import org.hibernate.classic.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import ar.edu.itba.it.paw.domain.AbstractHibernateRepo;

@Repository
public class HibernateMovieRepo extends AbstractHibernateRepo implements
		MovieRepo {

	@Autowired
	public HibernateMovieRepo(SessionFactory sessionFactory) {
		super(sessionFactory);
	}

	public List<Movie> getAll() {
		return find("from Movie");
	}

	public Movie get(int movieId) throws NoMovieIdException {
		try {
			return get(Movie.class, movieId);
		} catch (HibernateException e) {
			throw new NoMovieIdException();
		}

	}

	public List<Movie> getByGenre(String genre) throws NoGenreException {
		if (Movie.Genre.valueOf(genre) == null)
			throw new NoGenreException();
		return find("from Movie where genre = ?", Movie.Genre.valueOf(genre));
	}

	public List<Movie> getByDirector(String director) {
		// Queda ver como hacer que no sea exacto (como implementar ilike )
		return find("from Movie where director = ?", director);
	}

	@SuppressWarnings("unchecked")
	public List<Movie> getByRating(int limit) {
		Session session = getSession();

		Query query = session.createQuery(
				"select movie from Movie movie join movie.comments comment group by movie.id "
			  + "order by avg(comment.rating) desc").setMaxResults(limit);
		List<Movie> list = query.list();
		return list;
	}

	@SuppressWarnings("unchecked")
	public List<Movie> getByReleaseDate(Date from, Date to) {
		Session session = getSession();
		
		Query query = session.createQuery(
				"from Movie where releaseDate > :from and releaseDate < :to").setTimestamp("from", from).setTimestamp("to", to);
		List<Movie> list = query.list();
		return list;
	}

	@SuppressWarnings("unchecked")
	public List<Movie> getByCreationDate(int limit) {
		Session session = getSession();

		Query query = session.createQuery(
				"from Movie order by creationDate desc").setMaxResults(limit);
		List<Movie> list = query.list();
		return list;
	}

	@Override
	public void save(Movie movie) {
		super.save(movie);
	}

	@Override
	public void delete(Movie movie) {
		super.delete(movie);
	}

}
