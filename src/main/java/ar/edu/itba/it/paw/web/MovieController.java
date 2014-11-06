package ar.edu.itba.it.paw.web;

import java.io.BufferedOutputStream;
import java.io.IOException;
import java.sql.Date;
import java.util.Calendar;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import ar.edu.itba.it.paw.domain.genre.Genre;
import ar.edu.itba.it.paw.domain.genre.GenreRepo;
import ar.edu.itba.it.paw.domain.movie.Movie;
import ar.edu.itba.it.paw.domain.movie.MovieRepo;
import ar.edu.itba.it.paw.domain.prize.Prize;
import ar.edu.itba.it.paw.domain.prize.PrizeRepo;
import ar.edu.itba.it.paw.domain.user.NoAdminException;
import ar.edu.itba.it.paw.domain.user.User;
import ar.edu.itba.it.paw.web.command.MovieForm;
import ar.edu.itba.it.paw.web.validator.MovieFormValidator;

@Controller
public class MovieController {
	private MovieRepo movies;
	private GenreRepo genres;
	private PrizeRepo prices;
	private MovieFormValidator validator;
	private final int TOP_RANKED_CANT = 5;
	private final int MOST_RECENT_CANT = 5;
	private final int DEFAULT_BUFFER_SIZE = 102400;

	@Autowired
	public MovieController(MovieRepo movies, GenreRepo genres,
			MovieFormValidator validator, PrizeRepo prices) {
		this.movies = movies;
		this.genres = genres;
		this.validator = validator;
		this.prices = prices;
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
		User user = (User) req.getAttribute("user");
		if (user != null && user.canComment(movie)) {
			mav.addObject("canComment", true);
		} else {
			mav.addObject("canComment", false);
		}
		return mav;
	}

	@RequestMapping(method = RequestMethod.GET)
	public ModelAndView index(HttpServletRequest req) {
		Iterable<Movie> ranked = movies.getByRating(TOP_RANKED_CANT);
		Date now = new Date(System.currentTimeMillis());
		Calendar c = Calendar.getInstance();
		c.setTime(now);
		c.add(Calendar.DATE, -6);
		Iterable<Movie> releases = movies.getByReleaseDate(
				new Date(c.getTimeInMillis()), now);
		Iterable<Movie> recents = movies.getByCreationDate(MOST_RECENT_CANT);
		ModelAndView mav = new ModelAndView();
		mav.addObject("aWeekBefore", new Date(c.getTimeInMillis()));
		mav.addObject("recents", recents);
		mav.addObject("releases", releases);
		mav.addObject("ranked", ranked);
		return mav;
	}

	@RequestMapping(method = RequestMethod.GET)
	public ModelAndView create(HttpServletRequest req) throws Exception {
		User user = (User) req.getAttribute("user");
		if (user == null || !user.isAdmin()) {
			throw new NoAdminException();
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
			throw new NoAdminException();
		}
		ModelAndView mav = new ModelAndView();
		mav.addObject(new MovieForm(movie));
		mav.addObject("genresList", genres.getAll());
		return mav;
	}

	@RequestMapping(method = RequestMethod.POST)
	public ModelAndView edit(MovieForm movieForm, Errors errors,
			HttpServletRequest req) throws Exception {
		User user = (User) req.getAttribute("user");

		if (user == null || !user.isAdmin()) {
			throw new NoAdminException();
		}
		validator.validate(movieForm, errors);

		if(errors.hasErrors()){
			ModelAndView mav = new ModelAndView();
			mav.addObject("genresList", genres.getAll());
			return mav;
		}
		if (movieForm.isNew()) {
			movies.save(movieForm.build());
		} else {
			Movie oldMovie = movies.get(movieForm.getId());
			movieForm.update(oldMovie);
		}
		return new ModelAndView("redirect:./list");
	}

	@RequestMapping(method = RequestMethod.POST)
	public String delete(
			@RequestParam(value = "id", required = true) Movie movie,
			HttpServletRequest req) throws Exception {
		User user = (User) req.getAttribute("user");
		if (user == null || !user.isAdmin()) {
			throw new NoAdminException();
		}
		movies.delete(movie);
		return "redirect:./list";
	}

	@RequestMapping(method = RequestMethod.POST)
	public String addPrize(
			@RequestParam(value = "movieId", required = true) Movie movie,
			@RequestParam(value = "name", required = true) String name,
			@RequestParam(value = "prize", required = false) boolean prize,
			HttpServletRequest req) throws Exception {
		User user = (User) req.getAttribute("user");
		if (user == null || !user.isAdmin()) {
			throw new NoAdminException();
		}
		Prize newPrize = new Prize(movie, name, prize);
		movie.addPrize(newPrize);
		return "redirect:./detail?id=" + movie.getId();
	}

	@RequestMapping(method = RequestMethod.POST)
	public String deletePrize(
			@RequestParam(value = "prizeId", required = false) Prize prize,
			@RequestParam(value = "movieId", required = false) Movie movie,
			HttpServletRequest req) throws Exception {
		User user = (User) req.getAttribute("user");
		if (user == null || !user.isAdmin()) {
			throw new NoAdminException();
		}
		prices.delete(prize);
		return "redirect:./detail?id=" + movie.getId();
	}

	@RequestMapping(method = RequestMethod.POST)
	public String setPicture(
			@RequestParam(value = "movieId", required = true) Movie movie,
			@RequestParam(value = "pic", required = true) MultipartFile pic,
			HttpServletRequest req) throws Exception {
		User user = (User) req.getAttribute("user");
		if (user == null || !user.isAdmin()) {
			throw new NoAdminException();
		}
		movie.setPicture(pic.getBytes());
		return "redirect:./list";
	}

	@RequestMapping(method = RequestMethod.POST)
	public String deletePicture(
			@RequestParam(value = "movieId", required = true) Movie movie,
			HttpServletRequest req) throws Exception {
		User user = (User) req.getAttribute("user");
		if (user == null || !user.isAdmin()) {
			throw new NoAdminException();
		}
		movie.deletePicture();
		return "redirect:./list";
	}

	@RequestMapping(method = RequestMethod.GET)
	public void showPicture(
			@RequestParam(value = "movieId", required = true) Movie movie,
			HttpServletResponse resp) throws IOException {
		BufferedOutputStream output = null;
		try {
			byte[] picture = movie.getPicture();
			resp.reset();
			resp.setBufferSize(DEFAULT_BUFFER_SIZE);
			resp.setContentType("image/jpeg");
			resp.setHeader("Content-Length", String.valueOf(picture.length));
			output = new BufferedOutputStream(resp.getOutputStream(),
					DEFAULT_BUFFER_SIZE);
			output.write(picture, 0, picture.length);
		} catch (IOException e) {
			throw e;
		} finally {
			output.close();
		}
	}
	
}

// [ANDY] Como muestro la foto una vez que la tengo guardada?
