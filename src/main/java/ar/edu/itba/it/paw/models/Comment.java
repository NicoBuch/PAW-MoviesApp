package ar.edu.itba.it.paw.models;

import java.sql.Date;

public class Comment extends Entity{
	
	String body;
	Date creationDate;
	int rating; 
	Movie movie;
	User user;
	
	public Comment(long id, String body, int rating, Movie movie, User user) {
		super(id);
		creationDate = new Date(System.currentTimeMillis());
		setFields(body,rating,movie,user);
	}
	public Comment(long id, String body, Date creationDate, int rating, Movie movie, User user) {
		super(id);
		setFields(body, creationDate, rating,movie,user);
	}
	public Comment(String body, int rating, Movie movie, User user) {
		super();
		creationDate = new Date(System.currentTimeMillis());
		setFields(body,rating,movie,user);
		
	}
	public void setFields(String body, Date creationDate, int rating, Movie movie, User user){
		this.creationDate = creationDate;
		setFields(body, rating, movie, user);
	}
	
	private void setFields(String body, int rating, Movie movie, User user){
		if((rating > 5 || rating < 0) || movie == null || user == null){
			throw new IllegalArgumentException();
		}
		this.body = body;
		this.rating = rating;
		this.movie = movie;
		this.user = user;
	}
	public String getBody() {
		return body;
	}
	public Date getCreationDate() {
		return creationDate;
	}
	public int getRating() {
		return rating;
	}
	public Movie getMovie() {
		return movie;
	}
	public User getUser() {
		return user;
	}
	
}
