package ar.edu.itba.it.paw.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ar.edu.itba.it.paw.daos.CommentDao;
import ar.edu.itba.it.paw.exceptions.CantCommentBeforeMoviesReleaseDateException;
import ar.edu.itba.it.paw.exceptions.NoMoreThanOneCommentPerUserPerMovieException;
import ar.edu.itba.it.paw.models.Comment;
import ar.edu.itba.it.paw.models.Movie;
import ar.edu.itba.it.paw.models.MovieWithComments;
import ar.edu.itba.it.paw.models.User;
@Service
public class CommentServiceImpl implements CommentService{
	private CommentDao dao;
	private MovieService movieService;
	private UserService userService;
	@Autowired
	public CommentServiceImpl(CommentDao dao, MovieService movieService, UserService userService) {
		this.dao = dao;
		this.movieService = movieService;
		this.userService = userService;
	}

	public void save(Comment c)
			throws NoMoreThanOneCommentPerUserPerMovieException,
			CantCommentBeforeMoviesReleaseDateException {
		if (c.getMovie().getReleaseDate().after(c.getCreationDate()) && !c.getUser().isVip()) {
			throw new CantCommentBeforeMoviesReleaseDateException();
		}
		Comment usersComment = dao.getCommentsByUserAndMovie(c.getUser(),
				c.getMovie());
		if (usersComment != null) {
			throw new NoMoreThanOneCommentPerUserPerMovieException();
		}

		dao.save(c);
	}

	public Iterable<Comment> getCommentsByMovie(Movie m) {
		return dao.getCommentsByMovie(m);
	}

	public Iterable<Comment> getCommentsByUser(User u) {
		return dao.getCommentsByUser(u);
	}


	public int countCommentsByMovie(Movie m) {
		return dao.countCommentsByMovie(m);
	}
	public boolean alreadyComment(User user, Movie movie) {
		Comment userComment = dao.getCommentsByUserAndMovie(user,
				movie);
		if(userComment!=null){
			return true;
		}
		return false;
	}

	public boolean canComment(User user, Movie movie) {
		if(user==null)
			return false;
		if((movieService.alreadyRelease(movie) || userService.isVip(user)) && user!=null 
				&& !(alreadyComment(user, movie))){
			return true;
		}
		else{
			return false;
		}

	}
	
	public MovieWithComments getMovieWithComments(Movie movie){
		MovieWithComments movieComment = new MovieWithComments(movie, countCommentsByMovie(movie));
		return movieComment;
	}

}
