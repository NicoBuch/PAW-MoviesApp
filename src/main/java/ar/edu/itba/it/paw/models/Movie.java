package ar.edu.itba.it.paw.models;

import java.sql.Date;

public class Movie extends Entity {

	private String title;
	private Date releaseDate;
	private String director;
	private Genre genre;
	private int minutes;
	private String description;
	private Date creationDate;

	public enum Genre {
		ACTION, TERROR, THRILLER, DRAMA, PORN, COMEDY, ANIMATION, FANTASY, SCIFI
	}

	public Movie(String movieName, Date releaseDate,
			String directorName, String genre, int minutes, String description,
			Date creationDate) {
		setFields(movieName, directorName, minutes, genre, description,
				releaseDate, creationDate);
	}

	public Movie(String movieName, Date releaseDate, String directorName,
			String genre, int minutes, String description) {
		super();
		setFields(movieName, directorName, minutes, genre, description,
				releaseDate, new Date(System.currentTimeMillis()));
	}

	private void setFields(String movieName, String directorName, int minutes,
			String genre, String description, Date releaseDate,
			Date creationDate) {
		if (movieName.length() > 255 || directorName.length() > 255
				|| releaseDate == null || genre == null || description == null) {
			throw new IllegalArgumentException();
		}
		this.title = movieName;
		this.director = directorName;
		this.minutes = minutes;
		this.genre = Genre.valueOf(genre.toUpperCase());
		this.description = description;
		this.releaseDate = releaseDate;
		this.creationDate = creationDate;
	}

	public String getGenre() {
		return this.genre.toString();
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

	/* Se fija si es estreno la pelicula */
	public boolean isNew() {
		return releaseDate.after(new Date(System.currentTimeMillis() - 604800000));
	}

}
