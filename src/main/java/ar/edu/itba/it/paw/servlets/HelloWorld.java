package ar.edu.itba.it.paw.servlets;
import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import ar.edu.itba.it.paw.daos.MovieDao;
import ar.edu.itba.it.paw.daos.PostgresMovieDao;
import ar.edu.itba.it.paw.exceptions.NoMovieIdException;
import ar.edu.itba.it.paw.models.Movie;
import ar.edu.itba.it.paw.services.MovieService;
import ar.edu.itba.it.paw.services.MovieServiceImpl;

public class HelloWorld extends HttpServlet{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
	throws ServletException, IOException{
		MovieService ms = MovieServiceImpl.getInstance();
		Iterable<Movie> movies = ms.getAll();
		
		PrintWriter out = resp.getWriter();
		out.println("<html><body>HelloWorld!");
		try {
			Movie movie = ms.getById(5);
			ms.save(movie);
			out.println(movie.getTitle());
		} catch (NoMovieIdException e) {
			out.println(" ID INVALIDO");
			e.printStackTrace();
		}
		for(Movie mv : movies){
			out.println(mv.getTitle());
			out.println(" ");
			out.println("Director:" + mv.getDirector());
			out.println(" ");
			out.println("ReleaseDate:" + mv.getReleaseDate());
			out.println(" ");
			out.println("Director:" + mv.getDirector());
			out.println(" ");
			out.println("Duration:" + mv.getMinutes());
			out.println(" ");
			out.println("Description:" + mv.getDescription());
		}
		
		//md.update(new Movie("bla", new Date(System.currentTimeMillis()), "Director", "Fantasy", 20, "La peor"));
		out.println(" ");
		out.println("</body></html>");
		
	}
}

