package ar.edu.itba.it.paw.web;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import ar.edu.itba.it.paw.domain.comment.CantCommentBeforeMoviesReleaseDateException;
import ar.edu.itba.it.paw.domain.comment.Comment;
import ar.edu.itba.it.paw.domain.comment.CommentRepo;
import ar.edu.itba.it.paw.domain.movie.Movie;
import ar.edu.itba.it.paw.domain.user.NoMoreThanOneCommentPerUserPerMovieException;
import ar.edu.itba.it.paw.domain.user.User;

@Controller
public class CommentsController {

	private CommentRepo comments;

	@Autowired
	public CommentsController(CommentRepo comments) {
		this.comments = comments;
	}

	@RequestMapping(method = RequestMethod.POST)
	public ModelAndView delete(
			@RequestParam(value = "commentId", required = true) Comment comment,
			@RequestParam(value = "movieId", required = true) Movie movie,
			HttpServletRequest req) throws Exception {
		User user = (User) req.getAttribute("user");
		if (user == null || !user.isAdmin()) {
			throw new Exception();
		}
		comments.delete(comment);
		ModelAndView mav = new ModelAndView();
		mav.addObject("movie", movie);
		Iterable<Comment> commentsList = movie.getComments();
		mav.addObject("comments", commentsList);
		if (user.canComment(movie)) {
			mav.addObject("canComment", true);
		} else {
			mav.addObject("canComment", false);
		}
		mav.setViewName("movie/detail");
		return mav;
	}
	

	@RequestMapping(method = RequestMethod.POST)
	public ModelAndView create(
			@RequestParam(value = "movieId", required = true) Movie movie,
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
		if(user.canComment(movie)){
			Comment comment = new Comment(body, rating, movie, user);
			comments.save(comment);
		}
		Iterable<Comment> comments = movie.getComments();
		mav.addObject("comments", comments);
		mav.addObject("canComment", false);
		mav.setViewName("movie/detail");
		return mav;
	}

}
