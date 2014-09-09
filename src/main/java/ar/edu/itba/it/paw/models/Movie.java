package ar.edu.itba.it.paw.models;

import java.sql.Date;

public class Movie extends Entity{
	
	String movieName;
	Date releaseDate;
	String directorName;
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
		this.movieName = movieName;
		this.directorName = directorName;
		this.minutes = minutes;
		this.genre = Genre.valueOf(genre.toUpperCase());
		this.description = description;
		this.releaseDate = releaseDate;
	}
	
	public String getGenre(){
		return this.genre.toString();
	}
	public String getMovieName(){
		return this.movieName;
	}
	public String getDirectorName(){
		return this.directorName;
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
	
}
