package ar.edu.itba.it.paw.domain.movie;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.SortedSet;
import java.util.TreeSet;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;

import org.hibernate.annotations.Sort;
import org.hibernate.annotations.SortType;
import org.parse4j.Parse;
import org.parse4j.ParseException;
import org.parse4j.ParseObject;
import org.parse4j.ParseQuery;
import org.parse4j.callback.FindCallback;
import org.postgresql.util.Base64;

import ar.edu.itba.it.paw.domain.PersistentEntity;
import ar.edu.itba.it.paw.domain.comment.Comment;
import ar.edu.itba.it.paw.domain.genre.Genre;
import ar.edu.itba.it.paw.domain.prize.Prize;

@Entity
public class Movie extends PersistentEntity {

	private static final int DESCRIPTION_LENGTH = 300;
	private static final long ONE_WEEK_IN_MILLIS = 604800000;
	
	private String title;
	private Date releaseDate;
	private String director;

	@ManyToMany
	private Set<Genre> genres;
	
	@OneToMany(mappedBy="movie", cascade=CascadeType.ALL, orphanRemoval = true)
	private List<Prize> prices = new ArrayList<Prize>();
	
	private int minutes;
	private String description;
	private Date creationDate;
	private byte[] picture;
	private int visits;

	@OneToMany(mappedBy="movie", cascade=CascadeType.ALL)
	@Sort(type = SortType.NATURAL)
	private SortedSet<Comment> comments = new TreeSet<Comment>();

	@SuppressWarnings("unused")
	private Movie(){
	}

	public Movie(String movieName, Date releaseDate,
			String directorName, Set<Genre> genres, int minutes, String description,
			Date creationDate) {
		setFields(movieName, directorName, minutes, genres, description,
				releaseDate, creationDate);
	}

	public Movie(String movieName, Date releaseDate, String directorName,
			Set<Genre> genres, int minutes, String description) {
		super();
		setFields(movieName, directorName, minutes, genres, description,
				releaseDate, new Date(System.currentTimeMillis()));
	}

	private void setFields(String movieName, String directorName, int minutes,
			Set<Genre> genres, String description, Date releaseDate,
			Date creationDate) {
		if (movieName.length() > 255 || directorName.length() > 255 || movieName.isEmpty() || directorName.isEmpty()
				|| releaseDate == null || genres == null || genres.isEmpty() || description.isEmpty()) {
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

	public Set<Genre> getGenres() {
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
	public String getPictureURL() {
		if(picture == null) {
			return "";
		}
		String encodedImage = Base64.encodeBytes(picture);
		String url = "data:image/jpeg;base64," + encodedImage;
		return url;
	}
	public void setPicture(byte[] pic){
		this.picture = pic;
	}
	public byte[] getPicture(){
		return picture;
	}
	public Date getCreationDate() {
		return creationDate;
	}

	public void setDescription(String string) {
		this.description = string;
	}
	public String getShortDescription(){
		if(description.length() >= DESCRIPTION_LENGTH){
			return description.substring(0, DESCRIPTION_LENGTH) + "...";
		}
		return description;
	}
	
	public boolean isNew() {
		return releaseDate.after(new Date(System.currentTimeMillis() - ONE_WEEK_IN_MILLIS));
	}

	public SortedSet<Comment> getComments(){
		return comments;
	}
	
	 public void addComment(Comment comment) {
//		 if(comments.contains(comment)){
//			 return;
//		 }
		 comments.add(comment);
		 comment.getUser().getComments().add(comment);
//		 comment.getUser().addComment(comment);
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
	
	public void setGenres(Set<Genre> genres){
		if(genres != null && !genres.isEmpty()){
			for(Genre g : this.genres){
				if(!genres.contains(g)){	
					g.removeMovie(this);
				}
			}
			this.genres.clear();
			for(Genre g : genres){
				if(!this.genres.contains(g)){
					g.addMovie(this);
				}
			}
			this.genres.addAll(genres);
		}
		else{
			throw new IllegalArgumentException();
		}	
	}

	
	public void addGenre(Genre g) {
		if(!genres.add(g)){
			return;
		}
		g.addMovie(this);
	}

	public void removeGenre(Genre g) {
		if(!genres.remove(g)){
			return;
		}
		g.removeMovie(this);
	}

	public List<Prize> getPrices(){
		List<Prize> list= new ArrayList<Prize>();
		for(Prize p : prices){
			if(p.isPrize()){
				list.add(p);
			}
		}
		return list;
	}
	
	public List<Prize> getNominations(){
		List<Prize> list= new ArrayList<Prize>();
		for(Prize p : prices){
			if(!p.isPrize()){
				list.add(p);
			}
		}
		return list;
	}

	public void addPrize(Prize prize) {
		if(!prices.contains(prize)){
			prices.add(prize);
		}	
	}

	public void removePrize(Prize prize) {
		this.prices.remove(prize);
	}

	public void deletePicture() {
		picture = null;		
	}
	
	public double getAvgRating(){
		double rating = 0;
		for(Comment c : comments){
			rating+=c.getRating();
		}
		return rating/comments.size();
	}

	public int getVisits() {
		return visits;
	}

	public void visit() {
		this.visits++;
	}
	
	public void getStock() {
		Parse.initialize("bH0IAo6UbBCuaXVVFZQl62vgaOs6l4vBRmDmyZMl", "EwQWDYrnMcTqQ7MJOxnl3l9aA12bBHOPKXp3AIFY");
		ParseQuery<ParseObject> query = ParseQuery.getQuery("Movie");
		query.limit(1);
		query.findInBackground(new FindCallback<ParseObject>() {
		    public void done(List<ParseObject> movie, ParseException e) {
		        if (e == null) {
		            movie.get(0).getInt("stock");
		        } else {
		            throw new RuntimeException();
		        }
		    }
		});
		
	}


}
