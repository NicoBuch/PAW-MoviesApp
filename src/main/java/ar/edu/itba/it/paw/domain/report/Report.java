package ar.edu.itba.it.paw.domain.report;

import javax.persistence.ManyToOne;

import javax.persistence.Entity;

import ar.edu.itba.it.paw.domain.PersistentEntity;
import ar.edu.itba.it.paw.domain.comment.Comment;
import ar.edu.itba.it.paw.domain.user.User;

@Entity
public class Report extends PersistentEntity{
	
	@ManyToOne
	private User user;
	
	@ManyToOne
	private Comment comment;
	
	public Report(){
	}
	
	public Report(User user, Comment comment){
		if (!user.canReport(comment)){
			throw new IllegalArgumentException();
		}
		this.user = user;
		this.comment = comment;
	}
	
	public Comment getComment(){
		return comment;
	}
	
	public User getUser(){
		return user;
	}
}
