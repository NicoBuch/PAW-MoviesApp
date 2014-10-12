package ar.edu.itba.it.paw.web;

import java.sql.Date;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import ar.edu.itba.it.paw.exceptions.CantCommentBeforeMoviesReleaseDateException;
import ar.edu.itba.it.paw.exceptions.NoGenreException;
import ar.edu.itba.it.paw.exceptions.NoMoreThanOneCommentPerUserPerMovieException;
import ar.edu.itba.it.paw.models.Comment;
import ar.edu.itba.it.paw.models.Movie;
import ar.edu.itba.it.paw.models.MovieWithComments;
import ar.edu.itba.it.paw.models.User;
import ar.edu.itba.it.paw.services.CommentService;
import ar.edu.itba.it.paw.services.MovieService;

@Controller
public class MovieController {
	private MovieService movieService;
	private CommentService commentService;
	@Autowired
	public MovieController(MovieService movieService,CommentService commentService){
		this.movieService = movieService;
		this.commentService = commentService;
	}
	@RequestMapping(method = RequestMethod.GET)
	public ModelAndView list(@RequestParam(value = "genre", required=false) String genre,
							@RequestParam(value="director",required=false) String director){
		ModelAndView mav = new ModelAndView();
		Iterable<Movie> movies;
		if(genre!=null){
			try {
				movies = movieService.getByGenre(genre);
			} catch (NoGenreException e) {
				return mav;
			}
		}
		else if (director!=null){
			movies = movieService.getByDirector(director);
		}
		else{
			movies = movieService.getAll();
		}
		mav.addObject("movies", movies);
		Object[] genres = Movie.Genre.values();
		List<Object> genresIterable = new ArrayList<Object>();
		for(int i = 0; i< genres.length; i++){
			genresIterable.add(genres[i]);
		}
		mav.addObject("genres",genres);
		return mav;
	}
	@RequestMapping(method = RequestMethod.GET)
	public ModelAndView detail(@RequestParam(value="id",required=true)Movie movie,
								HttpServletRequest req){
		ModelAndView mav = new ModelAndView();
		mav.addObject("movie", movie);
		Iterable<Comment> comments = commentService.getCommentsByMovie(movie);
		mav.addObject("comments", comments);
		HttpSession session = req.getSession();
		User user = (User) session.getAttribute("user");
		if(commentService.canComment(user, movie)){
			mav.addObject("canComment", true);
		}
		else{
			mav.addObject("canComment", false);
		}
		return mav;
	}
	@RequestMapping(method = RequestMethod.POST)
	public ModelAndView detail(@RequestParam(value="id",required=true)Movie movie,
			@RequestParam(value="rating",required=true)Integer rating,
			@RequestParam(value="body",required=true)String body,
			HttpServletRequest req) throws NoMoreThanOneCommentPerUserPerMovieException, CantCommentBeforeMoviesReleaseDateException{
		ModelAndView mav = new ModelAndView();
		mav.addObject("movie", movie);
		HttpSession session = req.getSession();
		User user =(User) session.getAttribute("user");
		if(commentService.canComment(user, movie)){
			Comment comment = new Comment(body, rating, movie, user);
			commentService.save(comment);
		}
		Iterable<Comment> comments = commentService.getCommentsByMovie(movie);
		mav.addObject("comments", comments);
		mav.addObject("canComment",false);
		return mav;
	}
	
	@RequestMapping(method = RequestMethod.GET)
	public ModelAndView index(){
		Iterable<Movie> ranked = movieService.getByRating(5);
		Date now = new Date(System.currentTimeMillis());
		Calendar c = Calendar.getInstance();
		c.setTime(now);
		c.add(Calendar.DATE, -6); 
		Iterable<Movie> releases = movieService.getByReleaseDate(new Date(c.getTimeInMillis()), now);
		shortDescription(releases);
		Iterable<Movie> recents = movieService.getByCreationDate(5);
		List<MovieWithComments> moviesWithComments = new ArrayList<MovieWithComments>();
		for(Movie movie : recents){
			moviesWithComments.add(commentService.getMovieWithComments(movie));
		}
		ModelAndView mav = new ModelAndView();
		mav.addObject("recents", moviesWithComments);
		mav.addObject("releases", releases);
		mav.addObject("ranked", ranked);
		return mav;
	}
	private void shortDescription(Iterable<Movie> movies){
		for(Movie m : movies){
			if(m.getDescription().length()>300){
				m.setDescription(m.getDescription().substring(0, 300) + "...");
			}
		}
	}
}
