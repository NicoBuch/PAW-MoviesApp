package ar.edu.itba.it.paw.domain.prize;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;

import ar.edu.itba.it.paw.domain.PersistentEntity;
import ar.edu.itba.it.paw.domain.movie.Movie;

@Entity
public class Prize extends PersistentEntity{
	
	@ManyToOne
	private Movie movie;
	private String name;
	
	// True if it is a price, false if it is a nomination 
	private boolean prize;
	
	@SuppressWarnings("unused")
	private Prize(){
	}
	
	public Prize(Movie movie, String name, boolean prize){
		if(movie == null || name == null || name.isEmpty() || name.length() > 255){
			throw new IllegalArgumentException();
		}
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
	
}
