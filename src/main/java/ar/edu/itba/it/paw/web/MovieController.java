package ar.edu.itba.it.paw.web;

import java.sql.Date;
import java.util.Calendar;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import ar.edu.itba.it.paw.domain.comment.Comment;
import ar.edu.itba.it.paw.domain.comment.CommentRepo;
import ar.edu.itba.it.paw.domain.genre.Genre;
import ar.edu.itba.it.paw.domain.genre.GenreRepo;
import ar.edu.itba.it.paw.domain.movie.Movie;
import ar.edu.itba.it.paw.domain.movie.MovieRepo;
import ar.edu.itba.it.paw.domain.user.User;
import ar.edu.itba.it.paw.web.command.MovieForm;
import ar.edu.itba.it.paw.web.validator.MovieFormValidator;

@Controller
public class MovieController {
	private MovieRepo movies;
	private GenreRepo genres;
	private CommentRepo comments;
	private MovieFormValidator validator;
	private final int TOP_RANKED_CANT = 5;
	private final int MOST_RECENT_CANT = 5;

	@Autowired
	public MovieController(MovieRepo movies, GenreRepo genres, MovieFormValidator validator, CommentRepo comments) {
		this.movies = movies;
		this.genres = genres;
		this.validator = validator;
		this.comments = comments;
	}

	@RequestMapping(method = RequestMethod.GET)
	public ModelAndView list(
			@RequestParam(value = "genre", required = false) Genre genre,
			@RequestParam(value = "director", required = false) String director) {
		ModelAndView mav = new ModelAndView();
		List<Movie> filteredMovies;
		if (genre != null) {
			filteredMovies = movies.getByGenre(genre);
		} else if (director != null) {
			filteredMovies = movies.getByDirector(director);
		} else {
			filteredMovies = movies.getAll();
		}
		List<Genre> genresIterable = genres.getAll();
		mav.addObject("movies", filteredMovies);
		mav.addObject("genres", genresIterable);
		return mav;
	}

	@RequestMapping(method = RequestMethod.GET)
	public ModelAndView detail(
			@RequestParam(value = "id", required = true) Movie movie,
			HttpServletRequest req) {
		ModelAndView mav = new ModelAndView();
		mav.addObject("movie", movie);
		List<Comment> commentList = comments.getByMovie(movie);
		mav.addObject("comments", commentList);
		User user = (User) req.getAttribute("user");
		if (user != null && user.canComment(movie)) {
			mav.addObject("canComment", true);
		} else {
			mav.addObject("canComment", false);
		}
		return mav;
	}

	@RequestMapping(method = RequestMethod.GET)
	public ModelAndView index() {
		Iterable<Movie> ranked = movies.getByRating(TOP_RANKED_CANT);
		Date now = new Date(System.currentTimeMillis());
		Calendar c = Calendar.getInstance();
		c.setTime(now);
		c.add(Calendar.DATE, -6);
		Iterable<Movie> releases = movies.getByReleaseDate(
				new Date(c.getTimeInMillis()), now);
		Iterable<Movie> recents = movies.getByCreationDate(MOST_RECENT_CANT);
		ModelAndView mav = new ModelAndView();
		mav.addObject("recents", recents);
		mav.addObject("releases", releases);
		mav.addObject("ranked", ranked);
		return mav;
	}

	@RequestMapping(method = RequestMethod.GET)
	public ModelAndView create(HttpServletRequest req) throws Exception {
		User user = (User) req.getAttribute("user");
		if (user == null || !user.isAdmin()) {
			throw new Exception();
		}
		ModelAndView mav = new ModelAndView();
		mav.addObject(new MovieForm());
		mav.addObject("genresList", genres.getAll());
		mav.setViewName("movie/edit");
		return mav;
	}
	
	@RequestMapping(method = RequestMethod.GET)
	public ModelAndView edit(
			@RequestParam(value = "id", required = true) Movie movie,
			HttpServletRequest req) throws Exception {
		User user = (User) req.getAttribute("user");
		if (user == null || !user.isAdmin()) {
			throw new Exception();
		}
		ModelAndView mav = new ModelAndView();
		mav.addObject(new MovieForm(movie));
		mav.addObject("genresList", genres.getAll());
		return mav;
	}
	
	@RequestMapping(method = RequestMethod.POST)
	public ModelAndView edit(
			MovieForm movieForm, Errors errors,
			HttpServletRequest req) throws Exception {
		User user = (User) req.getAttribute("user");
		
		if (user == null || !user.isAdmin()) {
			throw new Exception();
		}
		validator.validate(movieForm, errors);
		if(errors.hasErrors()){
			return null;
		}
		if (movieForm.isNew()) {
			movies.save(movieForm.build());
		}
		else{
			Movie oldMovie = movies.get(movieForm.getId());
			movieForm.update(oldMovie);
		}
		return set_empty_list();
	}


	@RequestMapping(method = RequestMethod.POST)
	public ModelAndView delete(
			@RequestParam(value = "id", required = true) Movie movie,
			HttpServletRequest req) throws Exception {
		User user = (User) req.getAttribute("user");
		if (user == null || !user.isAdmin()) {
			throw new Exception();
		}
		movies.delete(movie);
		return set_empty_list();
	}
	
//	@RequestMapping(method = RequestMethod.POST)
//	public ModelAndView setPicture(
//			@RequestParam(value = "movieId", required = true) Movie movie,
//			ServletRequestDataBinder binder,
//			HttpServletRequest req) throws Exception {
//		User user = (User) req.getAttribute("user");
//		if (user == null || !user.isAdmin()) {
//			throw new Exception();
//		}
//		binder.registerCustomEditor(byte[].class, new ByteArrayMultipartFileEditor());
//		movie.setPicture();
//		return set_empty_list();
//	}
	
	private ModelAndView set_empty_list(){
		ModelAndView mav = new ModelAndView();
		mav.addObject("movies", movies.getAll());
		mav.addObject("genres", genres.getAll());
		mav.setViewName("movie/list");
		return mav;
	}
	
}

// [Pendiente: mejorar el manejo de errores y la deteccion de errores (validator) en los forms]