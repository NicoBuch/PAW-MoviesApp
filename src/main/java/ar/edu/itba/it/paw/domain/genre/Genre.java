package ar.edu.itba.it.paw.domain.genre;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.ManyToMany;

import ar.edu.itba.it.paw.domain.PersistentEntity;
import ar.edu.itba.it.paw.domain.movie.Movie;

@Entity
public class Genre extends PersistentEntity{
	
	@ManyToMany(mappedBy="genres")
	private List<Movie> movies;
	
	private String name;
	
	public Genre(){		
	}
	
	public Genre(String name){
		this.name = name.toLowerCase();
	}
	
	public String getName(){
		return name.toUpperCase();
	}
	
	public List<Movie> getMovies(){
		return movies;
	}
	

}
