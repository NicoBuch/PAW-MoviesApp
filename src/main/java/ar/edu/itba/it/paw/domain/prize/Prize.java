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

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result + ((movie == null) ? 0 : movie.hashCode());
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		result = prime * result + (prize ? 1231 : 1237);
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
		Prize other = (Prize) obj;
		if (movie == null) {
			if (other.movie != null)
				return false;
		} else if (!movie.equals(other.movie))
			return false;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		if (prize != other.prize)
			return false;
		return true;
	}
	
}
