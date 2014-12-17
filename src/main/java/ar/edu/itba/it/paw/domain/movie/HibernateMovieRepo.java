package ar.edu.itba.it.paw.domain.movie;

import java.sql.Date;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import ar.edu.itba.it.paw.domain.AbstractHibernateRepo;
import ar.edu.itba.it.paw.domain.NoIdException;
import ar.edu.itba.it.paw.domain.genre.Genre;

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

	public Movie get(int movieId) throws NoIdException {
		try {
			return get(Movie.class, movieId);
		} catch (HibernateException e) {
			throw new NoIdException();
		}

	}

	@SuppressWarnings("unchecked")
	public List<Movie> getByGenre(Genre genre){
		Criteria c = getSession().createCriteria(Movie.class, "movie");
		c.createAlias("movie.genres", "genres");
		c.add(Restrictions.eq("genres.id", genre.getId()));
		return c.list();
	}

	public List<Movie> getByDirector(String director) {
		return find("from Movie m where lower(m.director) like ?","%" + director.toLowerCase() + "%");
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
		Criteria c = getSession().createCriteria(Movie.class, "movie");
		c.add(Restrictions.between("releaseDate", from, to));
		return c.list();
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

	@SuppressWarnings("unchecked")
	@Override
	public List<Movie> getByVisits(int limit) {
		Session session = getSession();

		Query query = session.createQuery(
				"from Movie order by visits desc").setMaxResults(limit);
		List<Movie> list = query.list();
		return list;
	}

}
