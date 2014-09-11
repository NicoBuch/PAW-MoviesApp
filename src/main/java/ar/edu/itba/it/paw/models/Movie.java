package ar.edu.itba.it.paw.models;

import java.sql.Date;

public class Movie extends Entity{
	
	String title;
	Date releaseDate;
	String director;
	Genre genre;
	int minutes;
	String description;
	
	public enum Genre{
		ACTION, TERROR, THRILLER, DRAMA, PORN, COMEDY, ANIMATION, FANTASY, SCIFI
	}
	
	public Movie(long id, String movieName, Date releaseDate ,
				String directorName, String genre, int minutes, String description) {
		super(id);
		setFields(movieName,directorName, minutes, genre, description, releaseDate);
	}
	public Movie(String movieName, Date releaseDate ,
			String directorName, String genre, int minutes, String description) {
		super();
		setFields(movieName,directorName, minutes, genre, description, releaseDate);
	}
	private void setFields(String movieName, String directorName, int minutes, String genre , String description, Date releaseDate){
		this.title = movieName;
		this.director = directorName;
		this.minutes = minutes;
		this.genre = Genre.valueOf(genre.toUpperCase());
		this.description = description;
		this.releaseDate = releaseDate;
	}
	
	public String getGenre(){
		return this.genre.toString();
	}
	public String getTitle(){
		return this.title;
	}
	public String getDirector(){
		return this.director;
	}
	public Date getReleaseDate(){
		return this.releaseDate;
	}
	public int getMinutes(){
		return this.minutes;
	}
	public String getDescription(){
		return this.description;
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
	
}
