package ar.edu.itba.it.paw.domain.commentRating;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;

import ar.edu.itba.it.paw.domain.PersistentEntity;
import ar.edu.itba.it.paw.domain.comment.Comment;
import ar.edu.itba.it.paw.domain.user.User;

@Entity
public class CommentRating extends PersistentEntity{
	
	@ManyToOne
	private User user;
	
	@ManyToOne
	private Comment comment;
	
	int rating;
	
	@SuppressWarnings("unused")
	private CommentRating(){
	}
	
	public CommentRating(User user, Comment comment, int rating){
		if(rating < 0 || rating > 5 ||  !user.canRate(comment)){
			throw new IllegalArgumentException();
		}
		this.user = user;
		this.comment = comment;
		this.rating = rating;
	}
	
	public Comment getComment(){
		return comment;
	}
	
	public User getUser(){
		return user;
	}
	
	public int getRating(){
		return rating;
	}

}
