package ar.edu.itba.it.paw.models;

import java.sql.Date;

public class Movie extends Entity{
	
	long id;
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
		setFields(directorName, minutes, genre, description, releaseDate);
	}
	public Movie(String movieName, Date releaseDate ,
			String directorName, String genre, int minutes, String description) {
		super();
		setFields(directorName, minutes, genre, description, releaseDate);
	}
	private void setFields(String directorName, int minutes, String genre , String description, Date releaseDate){
		
		this.directorName = directorName;
		this.minutes = minutes;
		this.genre = Genre.valueOf(genre);
		this.description = description;
		this.releaseDate = releaseDate;
	}
}
