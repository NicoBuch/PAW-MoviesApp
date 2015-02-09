package ar.edu.itba.it.paw.domain.genre;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.ManyToMany;

import ar.edu.itba.it.paw.domain.PersistentEntity;
import ar.edu.itba.it.paw.domain.movie.Movie;

@Entity
public class Genre extends PersistentEntity{

	@ManyToMany(mappedBy="genres")
	private Set<Movie> movies = new HashSet<Movie>();

	private String name;

	@SuppressWarnings("unused")
	private Genre(){
	}

	public Genre(String name){
		if(name == null ||name.isEmpty() || name.length() > 255){
			throw new IllegalArgumentException();
		}
		this.name = name.toLowerCase();
	}
	public String toString(){
		return getName();
	}

	public String getName(){
		return name.toUpperCase();
	}

	public Set<Movie> getMovies(){
		return movies;
	}

	public void removeMovie(Movie movie) {
		if(!movies.remove(movie)){
			return;
		}
		movie.removeGenre(this);
	}

	public void addMovie(Movie movie) {
		if(!movies.add(movie)){
			return;
		}
		movie.addGenre(this);
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
