package ar.edu.itba.it.paw.daos;

import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import ar.edu.itba.it.paw.models.Movie;
import ar.edu.itba.it.paw.models.Movie.Genre;

public class PostgresMovieDao implements MovieDao{
	private static PostgresMovieDao  obj = null;
	
	private PostgresMovieDao(){	
		
	}
	public static MovieDao getInstance(){
		if(obj == null)
			obj = new PostgresMovieDao();
		System.out.println("Llego " + obj.toString());
		
		return obj;
	}
	
	public Iterable<Movie> getAll() {
		Session<Movie> query = new Session<Movie>();
		ResultSet rs = query.list("movie");
		List<Movie> movies = new ArrayList<Movie>();
		
		try {
			while(rs.next()){
				String movieName = rs.getString("moviename");
				String directorName = rs.getString("directorname");
				String genre = rs.getString("genre");
				int minutes = rs.getInt("minutes");
				String description = rs.getString("description");
				Date releaseDate = rs.getDate("releasedate");
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
			String movieName = rs.getString("moviename");
			String directorName = rs.getString("directorname");
			String genre = rs.getString("genre");
			int minutes = rs.getInt("minutes");
			String description = rs.getString("description");
			Date releaseDate = rs.getDate("releasedate");
			long movieid = rs.getLong("id");
			movie = new Movie(movieid,movieName,releaseDate,directorName,genre,minutes,description);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return movie;
	}

	public void update(Movie movie) {
		// TODO Auto-generated method stub
		
	}

	public Movie getByGenre(String genre) {
		
		return null;
	}

}
