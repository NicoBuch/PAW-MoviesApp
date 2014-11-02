package ar.edu.itba.it.paw.web.command;

import java.sql.Date;
import java.util.List;

import ar.edu.itba.it.paw.domain.genre.Genre;
import ar.edu.itba.it.paw.domain.movie.Movie;


public class MovieForm {
	private String title;
	private int releaseDay;
	private int releaseMonth;
	private int releaseYear;
	private String director;
	private int minutes;
	private String description;
	private List<Genre> genres;
	private int id;

	public MovieForm(){
	}

	@SuppressWarnings("deprecation")
	public MovieForm(Movie movie){
		this.id = movie.getId();
		this.title = movie.getTitle();
		this.releaseYear = movie.getReleaseDate().getYear() + 1900;
		this.releaseMonth = movie.getReleaseDate().getMonth();
		this.releaseDay = movie.getReleaseDate().getDate();
		this.director = movie.getDirector();
		this.minutes = movie.getMinutes();
		this.description = movie.getDescription();
		this.id = movie.getId();
		this.genres = movie.getGenres();
//		List<Genre> genresList = movie.getGenres();
//		this.genres = new Genre[genresList.size()];
//		for(int i = 0; i< genresList.size(); i++){
//			this.genres[i] = genresList.get(i);
//		}
	}

	public boolean isNew(){
		return id == 0;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public int getReleaseDay() {
		return releaseDay;
	}

	public void setReleaseDay(int releaseDay) {
		this.releaseDay = releaseDay;
	}

	public int getReleaseMonth() {
		return releaseMonth;
	}

	public void setReleaseMonth(int releaseMonth) {
		this.releaseMonth = releaseMonth;
	}

	public int getReleaseYear() {
		return releaseYear;
	}

	public void setReleaseYear(int releaseYear) {
		this.releaseYear = releaseYear;
	}

	public String getDirector() {
		return director;
	}

	public void setDirector(String director) {
		this.director = director;
	}

	public int getMinutes() {
		return minutes;
	}

	public void setMinutes(int minutes) {
		this.minutes = minutes;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}
	public Date getReleaseDate(){
		String releaseDate = releaseYear + "-" + releaseMonth + "-" + releaseDay;
		return Date.valueOf(releaseDate);
	}

	public List<Genre> getGenres(){
		return genres;
	}
	public void setGenres(List<Genre> genres){
		this.genres = genres;
	}

	public Movie build() {
//		List<Genre> list = new ArrayList<Genre>(Arrays.asList(genres));
		Movie movie = new Movie(title, getReleaseDate(), director, genres, minutes, description);
		return movie;
	}

	public void update(Movie movie) {
		movie.setTitle(title);
		movie.setDescription(description);
		movie.setDirector(director);
		movie.setReleaseDate(getReleaseDate());
//		movie.setGenres(new ArrayList<Genre>(Arrays.asList(genres)));
		movie.setGenres(genres);
		movie.setMinutes(minutes);
	}
}
