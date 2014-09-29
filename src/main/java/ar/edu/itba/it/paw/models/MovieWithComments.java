package ar.edu.itba.it.paw.models;


public class MovieWithComments {
	public MovieWithComments(Movie movie, int commentCount) {
		this.movie = movie;
		this.commentCount = commentCount;
	}
	private Movie movie;
	private int commentCount;
	public Movie getMovie() {
		return movie;
	}
	public int getCommentCount() {
		return commentCount;
	}
}
