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
	public Comment(String body, int rating, Movie movie, User user) {
		super();
		creationDate = new Date(System.currentTimeMillis());
		setFields(body,rating,movie,user);
		
	}
	private void setFields(String body, int rating, Movie movie, User user){
		if(rating > 5 || rating < 0){
			throw new IllegalArgumentException();
		}
		this.body = body;
		this.rating = rating;
		this.movie = movie;
		this.user = user;
	}
}
