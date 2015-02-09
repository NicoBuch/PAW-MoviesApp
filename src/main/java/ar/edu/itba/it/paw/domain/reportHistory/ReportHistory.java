package ar.edu.itba.it.paw.domain.reportHistory;

import java.util.Arrays;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Transient;

import ar.edu.itba.it.paw.domain.PersistentEntity;
import ar.edu.itba.it.paw.domain.movie.Movie;
import ar.edu.itba.it.paw.domain.user.User;


// Esta entidad representa el historial de comentarios reportados y se guarda la pelicula a la que se habia hecho el comentario, que decia el comentario y quien lo reportó
@Entity
@Table(name = "report_history")
public class ReportHistory extends PersistentEntity{
			
	@ManyToOne
	private User reporter;
	
	@ManyToOne
	private Movie movie;
	
	private String explanation;
	
	private String resolution;
	
	private String commentBody;
	
	private Date resolutionDate;
	
		
	@Transient
	private final String[] posibleResolutions = { "CommentDestroied", "ReportCleaned" }; 
	
	@SuppressWarnings("unused")
	private ReportHistory(){
	}
	
	public ReportHistory(User reporter, Movie movie, String explanation, String resolution, String commentBody){
		if(!Arrays.asList(posibleResolutions).contains(resolution)){
			throw new IllegalArgumentException();
		}
		this.resolutionDate = new Date(System.currentTimeMillis());
		this.commentBody = commentBody;
		this.resolution = resolution;
		this.reporter = reporter; 
		this.movie = movie;
		this.explanation = explanation;
	}
	
	public Movie getMovie(){
		return movie;
	}
	
	public User getReporter(){
		return reporter;
	}

	public String getExplanation() {
		return explanation;
	}

	public String getResolution() {
		return resolution;
	}

	public String getCommentBody() {
		return commentBody;
	}
	
	public Date getResolutionDate(){
		return resolutionDate;
	}


}
