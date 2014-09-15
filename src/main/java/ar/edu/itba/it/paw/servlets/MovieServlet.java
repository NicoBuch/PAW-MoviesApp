package ar.edu.itba.it.paw.servlets;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import ar.edu.itba.it.paw.exceptions.CantCommentBeforeMoviesReleaseDateException;
import ar.edu.itba.it.paw.exceptions.NoMoreThanOneCommentPerUserPerMovieException;
import ar.edu.itba.it.paw.exceptions.NoMovieIdException;
import ar.edu.itba.it.paw.models.Comment;
import ar.edu.itba.it.paw.models.Movie;
import ar.edu.itba.it.paw.models.User;
import ar.edu.itba.it.paw.services.CommentService;
import ar.edu.itba.it.paw.services.CommentServiceImpl;
import ar.edu.itba.it.paw.services.MovieService;
import ar.edu.itba.it.paw.services.MovieServiceImpl;

@SuppressWarnings("serial")
public class MovieServlet extends HttpServlet{

	
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException{
		HttpSession session = req.getSession();
		MovieService movieService = MovieServiceImpl.getInstance();
		CommentService commentService = CommentServiceImpl.getInstance();
		Long id;
		try{
			 id = Long.parseLong(req.getParameter("id"));
		}catch (NumberFormatException e) {
			resp.sendRedirect("movies");
			return;
		} 
		User user = (User) session.getAttribute("user");
		try {
			Movie movie  = movieService.getById(id);
			req.setAttribute("movie", movie);
			Iterable<Comment> comments = commentService.getCommentsByMovie(movie);
			req.setAttribute("comments", comments);
			if(commentService.canComment(user, movie)){
				req.setAttribute("canComment", true);
			}
			else{
				req.setAttribute("canComment", false);
			}
			
			req.getRequestDispatcher("/WEB-INF/jsp/detailMovie.jsp").forward(req, resp);
		} catch (NoMovieIdException e) {
			resp.sendRedirect("movies");
		}
			
	}
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException{
		HttpSession session = req.getSession();
		CommentService commentService = CommentServiceImpl.getInstance();
		User user = (User) session.getAttribute("user");
		MovieService movieService = MovieServiceImpl.getInstance();
		String id = (String) req.getParameter("movieId");
		Movie movie;
		try {
			movie = movieService.getById(Long.parseLong(id));
		} catch (NumberFormatException e) {
			resp.sendRedirect("movies");
			return;
		} catch (NoMovieIdException e) {
			resp.sendRedirect("movies");
			return;
		}
		if(user == null){
			resp.sendRedirect("login");
			return;
		}
		if(!commentService.canComment(user, movie)){
				doGet(req, resp);
				return;
		}
		else{
			int rating;
			try{
				rating = Integer.parseInt(req.getParameter("rating"));
			}catch (NumberFormatException e){
				resp.sendRedirect("movies");
				return;
			}
			String body = (String)req.getParameter("body");
			Comment comment = new Comment(body, rating, movie, user);
			try {
				commentService.save(comment);
			} catch (NoMoreThanOneCommentPerUserPerMovieException e) {
				doGet(req, resp);
				return;
			} catch (CantCommentBeforeMoviesReleaseDateException e) {
				doGet(req, resp);
				return;
			}
			doGet(req, resp);
			
		}
		
	}

}
