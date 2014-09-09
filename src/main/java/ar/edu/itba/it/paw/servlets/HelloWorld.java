package ar.edu.itba.it.paw.servlets;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import ar.edu.itba.it.paw.daos.MovieDao;
import ar.edu.itba.it.paw.daos.PostgresMovieDao;
import ar.edu.itba.it.paw.models.Movie;

public class HelloWorld extends HttpServlet{
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
	throws ServletException, IOException{
		MovieDao md = (PostgresMovieDao) PostgresMovieDao.getInstance();
		Iterable<Movie> movies = md.getAll();
		Movie movie = md.getById(1);
		PrintWriter out = resp.getWriter();
		out.println("<html><body>HelloWorld!");
		for(Movie mv : movies){
			out.println(mv.getMovieName());
			out.println(" ");
			out.println("Director:" + mv.getDirectorName());
			out.println(" ");
			out.println("ReleaseDate:" + mv.getReleaseDate());
			out.println(" ");
			out.println("Director:" + mv.getDirectorName());
			out.println(" ");
			out.println("Duration:" + mv.getMinutes());
			out.println(" ");
			out.println("Description:" + mv.getDescription());
		}
		out.println(movie.getMovieName());
		out.println(" ");
		out.println("</body></html>");
		
	}
}

