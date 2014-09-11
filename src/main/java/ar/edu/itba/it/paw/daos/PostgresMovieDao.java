package ar.edu.itba.it.paw.daos;

import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import ar.edu.itba.it.paw.models.Movie;

public class PostgresMovieDao implements MovieDao{
	private static PostgresMovieDao  obj = null;
	
	private PostgresMovieDao(){	
		
	}
	public static MovieDao getInstance(){
		if(obj == null)
			obj = new PostgresMovieDao();
		
		return obj;
	}
	
	public Iterable<Movie> getAll() {
		Session<Movie> query = new Session<Movie>();
		ResultSet rs = query.list("movie");
		List<Movie> movies = new ArrayList<Movie>();
		
		try {
			while(rs.next()){
				String movieName = rs.getString("title");
				String directorName = rs.getString("director");
				String genre = rs.getString("genre");
				int minutes = rs.getInt("minutes");
				String description = rs.getString("description");
				Date releaseDate = rs.getDate("release_date");
				long id = rs.getLong("id");
				Movie movie = new Movie(id,movieName,releaseDate,directorName,genre,minutes,description);
				movies.add(movie);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
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
			rs.next();
			String movieName = rs.getString("title");
			String directorName = rs.getString("director");
			String genre = rs.getString("genre");
			int minutes = rs.getInt("minutes");
			String description = rs.getString("description");
			Date releaseDate = rs.getDate("release_date");
			long movieid = rs.getLong("id");
			movie = new Movie(movieid,movieName,releaseDate,directorName,genre,minutes,description);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return movie;
	}

	public void update(Movie movie) {
		Session<Movie> session = new Session<Movie>();
		if(movie.getId() > 0){
			Object[] movieName = {"title",movie.getTitle()};
			Object[] releaseDate = {"release_date" , movie.getReleaseDate()};
			Object[] directorName = {"director" , movie.getDirector()};
			Object[] genre = {"genre" , movie.getGenre()};
			Object[] minutes = {"minutes" , movie.getMinutes()};
			Object[] description = {"description" , movie.getDescription()};
			session.add(Criteria.eq("id", movie.getId()));
			session.update("movie", movieName, releaseDate, directorName, genre, minutes, description);
		}
		else{
			session.insert("movie", null, movie.getReleaseDate(), movie.getTitle(), movie.getDirector()
					, movie.getGenre(),movie.getMinutes(), movie.getDescription() );
		}
	}

	public Iterable<Movie> getByGenre(String genre) {
		Session<Movie> session = new Session<Movie>();
		session.add(Criteria.eq("genre", genre));
		ResultSet rs = session.list("movie");
		List<Movie> movies = new ArrayList<Movie>();
		try {
			while(rs.next()){
				String movieName = rs.getString("moviename");
				String directorName = rs.getString("directorname");
				int minutes = rs.getInt("minutes");
				String description = rs.getString("description");
				Date releaseDate = rs.getDate("releasedate");
				long id = rs.getLong("id");
				Movie movie = new Movie(id,movieName,releaseDate,directorName,genre,minutes,description);
				movies.add(movie);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return movies;
	}

}
