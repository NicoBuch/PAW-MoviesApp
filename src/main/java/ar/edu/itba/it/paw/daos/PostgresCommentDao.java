package ar.edu.itba.it.paw.daos;

import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import ar.edu.itba.it.paw.models.Comment;
import ar.edu.itba.it.paw.models.Movie;
import ar.edu.itba.it.paw.models.User;

public class PostgresCommentDao implements CommentDao {
	private static PostgresCommentDao dao;

	private PostgresCommentDao() {

	}

	public static PostgresCommentDao getInstance() {
		if (dao == null) {
			dao = new PostgresCommentDao();
		}
		return dao;
	}

	public Iterable<Comment> getAll() {
		Session<Comment> query = new Session<Comment>();
		ResultSet rs = query.list("comment");
		List<Comment> comments = new ArrayList<Comment>();
		try {
			while (rs.next()) {
				
				Movie movie = PostgresMovieDao.getInstance().getById(
						rs.getInt("movie_id"));
				User user = PostgresUserDao.getInstance().getById(
						rs.getInt("user_id"));
				comments.add(buildComment(rs, movie, user));
			}
			query.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return comments;
	}

	public Comment getById(long id) {
		Session<Comment> query = new Session<Comment>();
		query.add(Criteria.eq("id", id));
		ResultSet rs = query.list("comment");
		try {
			if(rs.next() == true){
				Movie movie = PostgresMovieDao.getInstance().getById(
						rs.getInt("movie_id"));
				User user = PostgresUserDao.getInstance().getById(
						rs.getInt("user_id"));
				return buildComment(rs, movie, user);
			}
			query.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}

	public void save(Comment comment) {
		Session<Comment> session = new Session<Comment>();
		if(comment.getId() > 0){
			Object[] body = {"body",comment.getBody()};
			Object[] creationDate = {"creation_date" , comment.getCreationDate()};
			Object[] rating = {"rating" , comment.getRating()};
			Object[] movieId = { "movie_id" , comment.getMovie().getId() };
			Object[] userId = {"user_id" , comment.getUser().getId()};
			session.add(Criteria.eq("id", comment.getId()));
			session.update("comment", body, creationDate, rating, movieId, userId);
		}
		else{
			session.insert("comment", null, comment.getBody(), comment.getCreationDate(),comment.getRating()
					, comment.getMovie().getId(), comment.getUser().getId());
		}
		try {
			session.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

	public Comment getCommentsByUserAndMovie(User user, Movie movie) {
		Session<Comment> query = new Session<Comment>();
		query.add(Criteria.eq("user_id", user.getId()));
		query.add(Criteria.eq("movie_id", movie.getId()));
		ResultSet rs = query.list("comment");
		try {
			if(rs.next() == true){
				return buildComment(rs, movie, user);
			}
			query.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}

	public Iterable<Comment> getCommentsByMovie(Movie movie) {
		Session<Comment> query = new Session<Comment>();
		query.add(Criteria.eq("movie_id", movie.getId()));
		ResultSet rs = query.list("comment");
		List<Comment> comments = new ArrayList<Comment>();
		try {
			while (rs.next()) {
				User user = PostgresUserDao.getInstance().getById(
						rs.getInt("user_id"));
				comments.add(buildComment(rs, movie, user));
			}
			query.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return comments;
	}

	public Iterable<Comment> getCommentsByUser(User user) {
		Session<Comment> query = new Session<Comment>();
		query.add(Criteria.eq("user_id", user.getId()));
		query.add(new Order("creation_date", false));
		ResultSet rs = query.list("comment");
		List<Comment> comments = new ArrayList<Comment>();
		try {
			while (rs.next()) {
				Movie movie = PostgresMovieDao.getInstance().getById(
						rs.getInt("movie_id"));
				comments.add(buildComment(rs, movie, user));
			}
			query.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return comments;
	}

	public int countCommentsByMovie(Movie m) {
		Session<Comment> session = new Session<Comment>();
		String query = "select count(*) from comment where movie_id=" + m.getId();
		ResultSet rs = session.executeQuery(query);
		int count = 0;
		try {
			if(rs.next() == true){
				count = rs.getInt("count");
			}
			session.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return count;
		
	}
	
	private Comment buildComment(ResultSet rs, Movie m, User u) throws SQLException{
		String body = rs.getString("body");
		int rating = rs.getInt("rating");
		Date creationDate = rs.getDate("creation_date");
		long id = rs.getLong("id");
		return new Comment(id, body, creationDate, rating, m, u);
	}

}
