package ar.edu.itba.it.paw.domain.genre;

import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.ManyToMany;

import ar.edu.itba.it.paw.domain.PersistentEntity;
import ar.edu.itba.it.paw.domain.movie.Movie;

@Entity
public class Genre extends PersistentEntity{

	@ManyToMany(mappedBy="genres")
	private Set<Movie> movies;

	private String name;

	public Genre(){
	}

	public Genre(String name){
		if(name == null ||name.isEmpty() || name.length() > 255){
			throw new IllegalArgumentException();
		}
		this.name = name.toLowerCase();
	}

	public String getName(){
		return name.toUpperCase();
	}

	public Set<Movie> getMovies(){
		return movies;
	}

	public void removeMovie(Movie movie) {
		this.movies.remove(movie);
		movie.getGenres().remove(this);
	}

	public void addMovie(Movie movie) {
		this.movies.add(movie);
		movie.getGenres().add(this);
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (!super.equals(obj))
			return false;
		if (getClass() != obj.getClass())
			return false;
		Genre other = (Genre) obj;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		return true;
	}




}
