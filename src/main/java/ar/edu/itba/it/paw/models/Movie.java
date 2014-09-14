package ar.edu.itba.it.paw.models;

import java.sql.Date;

import ar.edu.itba.it.paw.services.CommentService;
import ar.edu.itba.it.paw.services.CommentServiceImpl;

public class Movie extends Entity {

	String title;
	Date releaseDate;
	String director;
	Genre genre;
	int minutes;
	String description;
	Date creationDate;

	public enum Genre {
		ACTION, TERROR, THRILLER, DRAMA, PORN, COMEDY, ANIMATION, FANTASY, SCIFI
	}

	public Movie(long id, String movieName, Date releaseDate,
			String directorName, String genre, int minutes, String description,
			Date creationDate) {
		super(id);
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

	public int getCommentCount() {
		CommentService cs = CommentServiceImpl.getInstance();
		return cs.countCommentsByMovie(this);
	}

	public void setTitle(String movieName) {
		this.title = movieName;
	}

	public void setReleaseDate(Date releaseDate) {
		this.releaseDate = releaseDate;
	}

	public void setDirector(String directorName) {
		this.director = directorName;
	}

	public void setGenre(Genre genre) {
		this.genre = genre;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Date getCreationDate() {
		return creationDate;
	}

}
