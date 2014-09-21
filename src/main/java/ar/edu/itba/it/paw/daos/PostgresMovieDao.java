package ar.edu.itba.it.paw.daos;

import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import ar.edu.itba.it.paw.exceptions.FatalErrorException;
import ar.edu.itba.it.paw.models.Movie;

public class PostgresMovieDao implements MovieDao {
	private static PostgresMovieDao obj = null;

	private PostgresMovieDao() {

	}

	public static MovieDao getInstance() {
		if (obj == null)
			obj = new PostgresMovieDao();

		return obj;
	}

	public Iterable<Movie> getAll() {
		Session<Movie> query = new Session<Movie>();
		query.add(new Order("release_date", false));
		ResultSet rs = query.list("movie");
		List<Movie> movies = new ArrayList<Movie>();

		try {
			while (rs.next()) {
				Movie movie = formMovie(rs);
				movies.add(movie);
			}
			query.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			throw new FatalErrorException();
		}
		// TODO Auto-generated method stub
		return movies;
	}

	public Movie getById(long id) {
		Session<Movie> query = new Session<Movie>();
		query.add(Criteria.eq("id", id));
		ResultSet rs = query.list("movie");
		Movie movie = null;
		try {
			if (rs.next() == true) {
				movie = formMovie(rs);
			}
			query.close();
		} catch (SQLException e) {
			throw new FatalErrorException();
		}
		return movie;
	}

	public Movie getByTitle(String title) {
		Session<Movie> query = new Session<Movie>();
		query.add(Criteria.eq("title", title));
		ResultSet rs = query.list("movie");
		Movie movie = null;
		try {
			if (rs.next() == true) {
				movie = formMovie(rs);
			}
			query.close();
		} catch (SQLException e) {
			throw new FatalErrorException();
		}
		return movie;
	}

	public void save(Movie movie) {
		Session<Movie> session = new Session<Movie>();
		if (movie.getId() > 0) {
			Object[] movieName = { "title", movie.getTitle() };
			Object[] releaseDate = { "release_date", movie.getReleaseDate() };
			Object[] directorName = { "director", movie.getDirector() };
			Object[] genre = { "genre", movie.getGenre() };
			Object[] minutes = { "minutes", movie.getMinutes() };
			Object[] description = { "description", movie.getDescription() };
			session.add(Criteria.eq("id", movie.getId()));
			session.update("movie", movieName, releaseDate, directorName,
					genre, minutes, description);
		} else {
			session.insert("movie", null, movie.getReleaseDate(),
					movie.getTitle(), movie.getDirector(), movie.getGenre(),
					movie.getMinutes(), movie.getDescription());
		}
		try {
			session.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			throw new FatalErrorException();
		}
	}

	public Iterable<Movie> getByGenre(String genre) {
		Session<Movie> session = new Session<Movie>();
		session.add(Criteria.eq("genre", genre));
		session.add(new Order("release_date", false));
		ResultSet rs = session.list("movie");
		List<Movie> movies = new ArrayList<Movie>();
		try {
			while (rs.next()) {
				Movie movie = formMovie(rs);
				movies.add(movie);
			}
			session.close();
		} catch (SQLException e) {
			throw new FatalErrorException();
		}
		return movies;
	}

	private Movie formMovie(ResultSet rs) throws SQLException {
		String movieName = rs.getString("title");
		String directorName = rs.getString("director");
		String genre = rs.getString("genre");
		int minutes = rs.getInt("minutes");
		String description = rs.getString("description");
		Date releaseDate = rs.getDate("release_date");
		long id = rs.getLong("id");
		Date creationDate = rs.getDate("creation_date");
		Movie movie = new Movie(movieName, releaseDate, directorName,
				genre, minutes, description, creationDate);
		movie.setId(id);
		return movie;
	}

	public Iterable<Movie> getByRating(int limit) {
		Session<Movie> session = new Session<Movie>();
		String query = "select movie_id from comment "
				+ " group by movie_id order by avg(rating) desc limit " + limit;
		ResultSet rs = session.executeQuery(query);
		List<Movie> movies = new ArrayList<Movie>();

		try {
			while (rs.next()) {
				Movie movie = getById(rs.getLong("movie_id"));
				movies.add(movie);
			}
			session.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			throw new FatalErrorException();
		}
		return movies;
	}

	public Iterable<Movie> getByReleaseDate(Date from, Date to) {
		Session<Movie> query = new Session<Movie>();
		query.add(Criteria.biggerOrEq("release_date", from));
		query.add(Criteria.lowerOrEq("release_date", to));
		ResultSet rs = query.list("movie");
		List<Movie> movies = new ArrayList<Movie>();
		try {
			while (rs.next()) {
				Movie movie = formMovie(rs);
				movies.add(movie);
			}
			query.close();
		} catch (SQLException e) {
			throw new FatalErrorException();
		}
		return movies;
	}

	public Iterable<Movie> getByCreationDate(int limit) {
		Session<Movie> session = new Session<Movie>();
		String query = "select * from movie order by creation_date desc limit "
				+ limit;
		ResultSet rs = session.executeQuery(query);
		List<Movie> movies = new ArrayList<Movie>();
		try {
			while (rs.next()) {
				Movie movie = formMovie(rs);
				movies.add(movie);
			}
			session.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			throw new FatalErrorException();
		}
		return movies;
	}

	public Iterable<Movie> getByDirector(String director) {
		Session<Movie> session = new Session<Movie>();
		session.add(Criteria.stringEq("director", director));
		session.add(new Order("release_date", false));
		ResultSet rs = session.list("movie");
		List<Movie> movies = new ArrayList<Movie>();
		try {
			while (rs.next()) {
				Movie movie = formMovie(rs);
				movies.add(movie);
			}
			session.close();
		} catch (SQLException e) {
			throw new FatalErrorException();
		}
		return movies;
	}

}
