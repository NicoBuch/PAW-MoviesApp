package ar.edu.itba.it.paw.domain.comment;

import java.sql.Date;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;

import ar.edu.itba.it.paw.domain.PersistentEntity;
import ar.edu.itba.it.paw.domain.movie.Movie;
import ar.edu.itba.it.paw.domain.user.User;

@Entity
public class Comment extends PersistentEntity{
	
	private String body;
	private Date creationDate;
	private int rating; 
	
	@ManyToOne
	private Movie movie;
	
	@ManyToOne
	private User user;
	
	public Comment(){
	}

	public Comment( String body, Date creationDate, int rating, Movie movie, User user) {
		setFields(body, creationDate, rating,movie,user);
	}
	public Comment(String body, int rating, Movie movie, User user) {
		super();
		creationDate = new Date(System.currentTimeMillis());
		setFields(body,rating,movie,user);
		
	}
	private void setFields(String body, Date creationDate, int rating, Movie movie, User user){
		this.creationDate = creationDate;
		setFields(body, rating, movie, user);
	}
	
	private void setFields(String body, int rating, Movie movie, User user){
		if((rating > 5 || rating < 0) || movie == null || user == null || body.isEmpty()){
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
