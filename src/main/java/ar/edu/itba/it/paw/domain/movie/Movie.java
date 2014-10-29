package ar.edu.itba.it.paw.domain.movie;

import java.sql.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;

import ar.edu.itba.it.paw.domain.PersistentEntity;
import ar.edu.itba.it.paw.domain.comment.Comment;
import ar.edu.itba.it.paw.domain.genre.Genre;

@Entity
public class Movie extends PersistentEntity {


	private String title;
	private Date releaseDate;
	private String director;

	@ManyToMany
	private List<Genre> genres;
	private int minutes;
	private String description;
	private Date creationDate;

	
	@OneToMany(mappedBy="movie", cascade=CascadeType.ALL)
	private List<Comment> comments;

	public Movie(){
	}

	public Movie(String movieName, Date releaseDate,
			String directorName, List<Genre> genres, int minutes, String description,
			Date creationDate) {
		setFields(movieName, directorName, minutes, genres, description,
				releaseDate, creationDate);
	}

	public Movie(String movieName, Date releaseDate, String directorName,
			List<Genre> genres, int minutes, String description) {
		super();
		setFields(movieName, directorName, minutes, genres, description,
				releaseDate, new Date(System.currentTimeMillis()));
	}

	private void setFields(String movieName, String directorName, int minutes,
			List<Genre> genres, String description, Date releaseDate,
			Date creationDate) {
		if (movieName.length() > 255 || directorName.length() > 255
				|| releaseDate == null || genres == null ||genres.isEmpty() || description == null) {
			throw new IllegalArgumentException();
		}
		this.title = movieName;
		this.director = directorName;
		this.minutes = minutes;
		this.genres = genres;
		this.description = description;
		this.releaseDate = releaseDate;
		this.creationDate = creationDate;
	}

	public List<Genre> getGenres() {
		return genres;
	}

	public String getTitle() {
		return this.title;
	}

	public String getDirector() {
		return this.director;
	}

	public Date getReleaseDate() {
		return this.releaseDate;
	}

	public int getMinutes() {
		return this.minutes;
	}

	public String getDescription() {
		return this.description;
	}

	public Date getCreationDate() {
		return creationDate;
	}

	public void setDescription(String string) {
		this.description = string;
	}

	public boolean isNew() {
		return releaseDate.after(new Date(System.currentTimeMillis() - 604800000));
	}

	public List<Comment> getComments(){
		return comments;
	}

	public int getCommentCount(){
		return comments.size();
	}

	public boolean alreadyReleased(){
		return releaseDate.before(new Date(System.currentTimeMillis()));
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result + ((title == null) ? 0 : title.hashCode());
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
		Movie other = (Movie) obj;
		if (title == null) {
			if (other.title != null)
				return false;
		} else if (!title.equals(other.title))
			return false;
		return true;
	}

	public void setTitle(String title) {
		this.title = title;		
	}

	public void setReleaseDate(Date releaseDate) {
		this.releaseDate = releaseDate;
		
	}

	public void setDirector(String director) {
		this.director = director;
		
	}

	public void setMinutes(Integer minutes) {
		this.minutes = minutes;
		
	}

	public void setGenres(List<Genre> genres){
		if(genres != null && !genres.isEmpty()){
			for(Genre g : genres){
				if(!this.genres.contains(g)){
					this.genres.add(g);
				}
			}
			for(int i=0; i< this.genres.size();i++){
				Genre g = this.genres.get(i);
				if(!genres.contains(g)){
					this.genres.remove(g);
				}
			}
		}
		else{
			throw new IllegalArgumentException();
		}
			
	}


}
