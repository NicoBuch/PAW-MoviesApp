package ar.edu.itba.it.paw.web;

import java.sql.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import ar.edu.itba.it.paw.domain.comment.CantCommentBeforeMoviesReleaseDateException;
import ar.edu.itba.it.paw.domain.comment.Comment;
import ar.edu.itba.it.paw.domain.movie.Movie;
import ar.edu.itba.it.paw.domain.movie.MovieRepo;
import ar.edu.itba.it.paw.domain.movie.NoGenreException;
import ar.edu.itba.it.paw.domain.user.NoMoreThanOneCommentPerUserPerMovieException;
import ar.edu.itba.it.paw.domain.user.User;

@Controller
public class MovieController {
	private MovieRepo movies;
	private final int DESCRIPTION_LENGTH = 300;
	private final int TOP_RANKED_CANT = 5;
	private final int MOST_RECENT_CANT = 5;

	@Autowired
	public MovieController(MovieRepo movies) {
		this.movies = movies;
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
		User user = (User) req.getAttribute("user");
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
		User user = (User) req.getAttribute("user");
		if (user == null || !user.canComment(movie)) {
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

	@RequestMapping(method = RequestMethod.GET)
	public ModelAndView create(HttpServletRequest req) throws Exception {
		User user = (User) req.getAttribute("user");
		if (user == null || !user.isAdmin()) {
			throw new Exception();
		}
		return new ModelAndView().addObject("genres", Movie.Genre.values());
	}

	@RequestMapping(method = RequestMethod.POST)
	public ModelAndView create(
			@RequestParam(value = "title", required = true) String title,
			@RequestParam(value = "releaseDay", required = false) Integer releaseDay,
			@RequestParam(value = "releaseMonth", required = false) Integer releaseMonth,
			@RequestParam(value = "releaseYear", required = false) Integer releaseYear,
			@RequestParam(value = "director", required = true) String director,
			@RequestParam(value = "minutes", required = true) Integer minutes,
			@RequestParam(value = "description", required = true) String description,
			@RequestParam(value = "genre", required = true) String genre,
			HttpServletRequest req) throws Exception {
		User user = (User) req.getAttribute("user");
		if (user == null || !user.isAdmin()) {
			throw new Exception();
		}
		String releaseDate = releaseYear + "-" + releaseMonth + "-" + releaseDay;	
		Movie movie = new Movie(title, Date.valueOf(releaseDate), director, genre, minutes,
				description);
		movies.save(movie);
		ModelAndView mav = new ModelAndView();
		mav.setViewName("movie/list");
		mav.addObject("movies", movies.getAll());
		return mav;
	}

	@RequestMapping(method = RequestMethod.POST)
	public ModelAndView edit(
			@RequestParam(value = "title", required = false) String title,
			@RequestParam(value = "releaseDay", required = false) Integer releaseDay,
			@RequestParam(value = "releaseMonth", required = false) Integer releaseMonth,
			@RequestParam(value = "releaseYear", required = false) Integer releaseYear,
			@RequestParam(value = "director", required = false) String director,
			@RequestParam(value = "minutes", required = false) Integer minutes,
			@RequestParam(value = "description", required = false) String description,
			@RequestParam(value = "genre", required = false) String genre,
			@RequestParam(value = "id", required = true) Movie movie,
			HttpServletRequest req, HttpServletResponse resp) throws Exception {
		User user = (User) req.getAttribute("user");
		if (user == null || !user.isAdmin()) {
			throw new Exception();
		}
		if (!title.isEmpty()) {
			movie.setTitle(title);
		}
		String releaseDate = releaseYear + "-" + releaseMonth + "-" + releaseDay;
		if (!releaseDate.isEmpty() && isValidDate(releaseDate)) {
			movie.setReleaseDate(Date.valueOf(releaseDate));
		}
		if (!director.isEmpty()) {
			movie.setDirector(director);
		}
		if (minutes != null) {
			movie.setMinutes(minutes);
		}
		if (!description.isEmpty()) {
			movie.setDescription(description);
		}
		if (genre != null) {
			movie.setGenre(genre);
		}
		ModelAndView mav = new ModelAndView();
		mav.setViewName("movie/list");
		mav.addObject("movies", movies.getAll());
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
		mav.addObject("movie", movie);
		mav.addObject("genres", Movie.Genre.values());
		return mav;
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
		ModelAndView mav = new ModelAndView();
		mav.setViewName("movie/list");
		mav.addObject("movies", movies.getAll());
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

	public static boolean isValidDate(String inDate) {
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
		dateFormat.setLenient(false);
		try {
			dateFormat.parse(inDate.trim());
		} catch (ParseException pe) {
			return false;
		}
		return true;
	}
}
