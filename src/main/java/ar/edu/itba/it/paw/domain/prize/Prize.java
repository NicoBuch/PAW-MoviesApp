package ar.edu.itba.it.paw.domain.prize;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;

import ar.edu.itba.it.paw.domain.PersistentEntity;
import ar.edu.itba.it.paw.domain.movie.Movie;

@Entity
public class Prize extends PersistentEntity{
	
	@ManyToOne
	Movie movie;
	String name;
	
	// True if it is a price, false if it is a nomination 
	boolean prize;
	
	public Prize(){
	}
	
	public Prize(Movie movie, String name, boolean prize){
		this.movie = movie;
		this.name = name;
		this.prize = prize;
	}

	public Movie getMovie() {
		return movie;
	}

	public String getName() {
		return name;
	}

	public boolean isPrize() {
		return prize;
	}

	public void setMovie(Movie movie) {
		this.movie = movie;
		
	}
	
	
}
