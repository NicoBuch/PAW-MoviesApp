package ar.edu.itba.it.paw.web;

import java.sql.Date;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import ar.edu.itba.it.paw.domain.MovieRepo;
import ar.edu.itba.it.paw.domain.UserRepo;
import ar.edu.itba.it.paw.exceptions.CantCommentBeforeMoviesReleaseDateException;
import ar.edu.itba.it.paw.exceptions.NoGenreException;
import ar.edu.itba.it.paw.exceptions.NoMoreThanOneCommentPerUserPerMovieException;
import ar.edu.itba.it.paw.models.Comment;
import ar.edu.itba.it.paw.models.Movie;
import ar.edu.itba.it.paw.models.User;

@Controller
public class MovieController {
	private MovieRepo movies;
	private UserRepo users;
	private final int DESCRIPTION_LENGTH = 300;
	private final int TOP_RANKED_CANT = 5;
	private final int MOST_RECENT_CANT = 5;

	@Autowired
	public MovieController(MovieRepo movies, UserRepo users) {
		this.movies = movies;
		this.users = users;
	}

	@RequestMapping(method = RequestMethod.GET)
	public ModelAndView list(
			@RequestParam(value = "genre", required = false) String genre,
			@RequestParam(value = "director", required = false) String director) {
		ModelAndView mav = new ModelAndView();
		List<Movie> filteredMovies;
		if (genre != null) {
			try {
				filteredMovies = movies.getByGenre(genre);
			} catch (NoGenreException e) {
				return mav;
			}
		} else if (director != null) {
			filteredMovies = movies.getByDirector(director);
		} else {
			filteredMovies = movies.getAll();
		}
		mav.addObject("movies", filteredMovies);
		Object[] genres = Movie.Genre.values();
		List<Object> genresIterable = new ArrayList<Object>();
		for (int i = 0; i < genres.length; i++) {
			genresIterable.add(genres[i]);
		}
		mav.addObject("genres", genres);
		return mav;
	}

	@RequestMapping(method = RequestMethod.GET)
	public ModelAndView detail(
			@RequestParam(value = "id", required = true) Movie movie,
			HttpServletRequest req) {
		ModelAndView mav = new ModelAndView();
		mav.addObject("movie", movie);
		Iterable<Comment> comments = movie.getComments();
		mav.addObject("comments", comments);
		// HttpSession session = req.getSession();
		User user = (User) req.getAttribute("user");
		// User user = users.get(user_id);
		if (user != null && user.canComment(movie)) {
			mav.addObject("canComment", true);
		} else {
			mav.addObject("canComment", false);
		}
		return mav;
	}

	@RequestMapping(method = RequestMethod.POST)
	public ModelAndView detail(
			@RequestParam(value = "id", required = true) Movie movie,
			@RequestParam(value = "rating", required = true) Integer rating,
			@RequestParam(value = "body", required = true) String body,
			HttpServletRequest req) throws Exception,
			NoMoreThanOneCommentPerUserPerMovieException,
			CantCommentBeforeMoviesReleaseDateException {
		ModelAndView mav = new ModelAndView();
		mav.addObject("movie", movie);
		// HttpSession session = req.getSession();
		User user = (User) req.getAttribute("user");
		if (user == null || !user.canComment(movie)) {
			// manejo de errores?
			throw new Exception();
		}
		user.comment(body, rating, movie);
		Iterable<Comment> comments = movie.getComments();
		mav.addObject("comments", comments);
		mav.addObject("canComment", false);
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
		shortDescription(releases);
		ModelAndView mav = new ModelAndView();
		mav.addObject("recents", recents);
		mav.addObject("releases", releases);
		mav.addObject("ranked", ranked);
		return mav;
	}

	private void shortDescription(Iterable<Movie> movies) {
		for (Movie m : movies) {
			if (m.getDescription().length() > 300) {
				m.setDescription(m.getDescription().substring(0,
						DESCRIPTION_LENGTH)
						+ "...");
			}
		}
	}
}
