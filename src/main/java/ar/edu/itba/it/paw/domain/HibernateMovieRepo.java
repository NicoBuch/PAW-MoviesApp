package ar.edu.itba.it.paw.domain;

import java.sql.Date;
import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import ar.edu.itba.it.paw.exceptions.NoGenreException;
import ar.edu.itba.it.paw.exceptions.NoMovieIdException;
import ar.edu.itba.it.paw.models.Movie;

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
		return find("from Movie where genre = ?", genre);
	}

	public List<Movie> getByDirector(String director) {
		return find("from Movie where director = ?", director);
	}

	public List<Movie> getByRating(int limit) {
		return find("select movie from Movie movie join movie.comments comment group by movie.id order by avg(comment.rating) desc limit "
				+ limit);
	}

	public List<Movie> getByReleaseDate(Date from, Date to) {
		return find("from Movie where releaseDate > ? and releaseDate < ?",
				from, to);
	}

	public List<Movie> getByCreationDate(int limit) {
		return find("from Movie order by creationDate desc limit " + limit);
	}

}