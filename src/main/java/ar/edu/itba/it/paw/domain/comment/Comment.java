package ar.edu.itba.it.paw.domain.comment;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

import ar.edu.itba.it.paw.domain.PersistentEntity;
import ar.edu.itba.it.paw.domain.commentRating.CommentRating;
import ar.edu.itba.it.paw.domain.movie.Movie;
import ar.edu.itba.it.paw.domain.report.Report;
import ar.edu.itba.it.paw.domain.user.User;

@Entity
public class Comment extends PersistentEntity implements Comparable<Comment>{
	
	private String body;
	private Date creationDate;
	private int rating; 
	
	
	
	@OneToMany(mappedBy="comment", cascade=CascadeType.ALL)
	private List<CommentRating> commentRatings;
	
	@ManyToOne
	private Movie movie;
	
	@ManyToOne
	private User user;
	
	@OneToMany(mappedBy="comment" ,  cascade=CascadeType.ALL )
	private List<Report> reports;
	
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
	public void clean(){
		reports = new ArrayList<Report>();
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
	public void setMovie(Movie movie){
		this.movie = movie;
	}
	public User getUser() {
		return user;
	}
	public void setUser(User u){
		this.user = u;
	}
	public List<Report> getReports(){
		return reports;
	}
	
	public List<CommentRating> getCommentRatings(){
		return commentRatings;
	}
	
	public void rate(User user, int rating){
		commentRatings.add(new CommentRating(user, this, rating));
	}
	
	 public int compareTo(Comment comment) {
		int compare = this.getAvgCommentRatings().compareTo(comment.getAvgCommentRatings());
		if (compare == 0){
			return 1;
		}
		return -compare;
	 }

	public Double getAvgCommentRatings(){
		if(commentRatings == null || commentRatings.size() == 0){
			return Double.valueOf(0);
		}
		int sum = 0;
		for(CommentRating cr : commentRatings){
			sum += cr.getRating();
		}
		return Double.valueOf(sum/commentRatings.size());
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (!super.equals(obj))
			return false;
		if (getClass() != obj.getClass())
			return false;
		return true;
	}

	public void rate(CommentRating commentRating) {
		commentRatings.add(commentRating);
		commentRating.setComment(this);
	}

	public void addReport(Report report) {
		reports.add(report);
		report.setComment(this);
		
	}

	public void cleanReports() {
		reports.clear();		
	}
	

}
