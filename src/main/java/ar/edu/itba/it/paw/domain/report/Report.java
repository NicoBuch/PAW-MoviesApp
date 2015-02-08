package ar.edu.itba.it.paw.domain.report;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;

import ar.edu.itba.it.paw.domain.PersistentEntity;
import ar.edu.itba.it.paw.domain.comment.Comment;
import ar.edu.itba.it.paw.domain.user.User;

@Entity
public class Report extends PersistentEntity{
	
	@ManyToOne
	private User user;
	
	@ManyToOne
	private Comment comment;
	
	private String explanation;
	
	@SuppressWarnings("unused")
	private Report(){
	}
	
	public Report(User user, Comment comment, String explanation){
		if (!user.canReport(comment)){
			throw new IllegalArgumentException();
		}
		this.user = user;
		this.comment = comment;
		this.explanation = explanation;
	}
	
	public Comment getComment(){
		return comment;
	}
	
	public User getUser(){
		return user;
	}

	public String getExplanation() {
		return explanation;
	}

}
