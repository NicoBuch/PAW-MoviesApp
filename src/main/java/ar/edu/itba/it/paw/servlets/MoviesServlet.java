package ar.edu.itba.it.paw.servlets;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import ar.edu.itba.it.paw.exceptions.NoGenreException;
import ar.edu.itba.it.paw.models.Movie;
import ar.edu.itba.it.paw.services.MovieService;
import ar.edu.itba.it.paw.services.MovieServiceImpl;

public class MoviesServlet extends HttpServlet{

	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException{
		
		resp.setContentType("text/html");
		String genre = req.getParameter("genre");
		MovieService movieService = MovieServiceImpl.getInstance();
		Iterable<Movie> movies = new ArrayList<Movie>(); //Inicializo con una lista vacia, programacion defensiva
		if(genre == null){
			movies = movieService.getAll();
		}
		else{
			try {
				movies = movieService.getByGenre(genre);
			} catch (NoGenreException e) {
				resp.sendRedirect("movies");
			}
		}
		Object[] genres = Movie.Genre.values();
		List<Object> genresIterable = new ArrayList<Object>();
		for(int i = 0; i< genres.length; i++){
			genresIterable.add(genres[i]);
		}
		req.setAttribute("movies", movies);
		req.setAttribute("genres", genresIterable);
		req.getRequestDispatcher("/WEB-INF/jsp/listMovies.jsp").forward(req, resp);
	}
}
