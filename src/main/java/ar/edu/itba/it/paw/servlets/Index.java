package ar.edu.itba.it.paw.servlets;

import java.io.IOException;
import java.sql.Date;
import java.util.Calendar;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import ar.edu.itba.it.paw.models.Movie;
import ar.edu.itba.it.paw.services.MovieService;
import ar.edu.itba.it.paw.services.MovieServiceImpl;

public class Index extends HttpServlet{

	private static final long serialVersionUID = 1L;
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		MovieService ms = MovieServiceImpl.getInstance();
		Iterable<Movie> ranked = ms.getByRating(5);
		Date now = new Date(System.currentTimeMillis());
		Calendar c = Calendar.getInstance();
		c.setTime(now);
		c.add(Calendar.DATE, -6); 
		Iterable<Movie> releases = ms.getByReleaseDate(new Date(c.getTimeInMillis()), now);
		shortDescription(releases);
		Iterable<Movie> recents = ms.getByCreationDate(5);
		req.setAttribute("recents", recents);
		req.setAttribute("user", req.getSession().getAttribute("user"));
		req.setAttribute("releases", releases);
		req.setAttribute("ranked", ranked);
		req.getRequestDispatcher("/WEB-INF/jsp/index.jsp").forward(req, resp);
	}
	
	private void shortDescription(Iterable<Movie> movies){
		for(Movie m : movies){
			if(m.getDescription().length()>300){
				m.setDescription(m.getDescription().substring(0, 300) + "...");
			}
		}
	}

}
