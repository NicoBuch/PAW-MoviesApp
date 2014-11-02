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
import ar.edu.itba.it.paw.domain.commentRating.CommentRating;
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
		return set_detail(movie, user.canComment(movie));
	}
	

	@RequestMapping(method = RequestMethod.POST)
	public ModelAndView create(
			@RequestParam(value = "movieId", required = true) Movie movie,
			@RequestParam(value = "rating", required = true) Integer rating,
			@RequestParam(value = "body", required = true) String body,
			HttpServletRequest req) throws Exception,
			NoMoreThanOneCommentPerUserPerMovieException,
			CantCommentBeforeMoviesReleaseDateException {
		User user = (User) req.getAttribute("user");
		if (user == null || !user.canComment(movie)) {
			throw new Exception();
		}
		if(user.canComment(movie)){
			Comment comment = new Comment(body, rating, movie, user);
			comments.save(comment);
		}
		return set_detail(movie, false);
	}
	
	@RequestMapping(method = RequestMethod.POST)
	public ModelAndView rate(
			@RequestParam(value = "commentId", required = true) Comment comment,
			@RequestParam(value = "rating", required = true) int rating,
			HttpServletRequest req) throws Exception{
		User user = (User) req.getAttribute("user");
		if(user == null){
			throw new Exception();
		}
		comments.rate(new CommentRating(user, comment, rating));
		return set_detail(comment.getMovie(), user.canComment(comment.getMovie()));
	}
	
	private ModelAndView set_detail(Movie movie, boolean canComment){
		ModelAndView mav = new ModelAndView();
		mav.addObject("movie", movie);
		Iterable<Comment> commentList = comments.getByMovie(movie);
		mav.addObject("comments", commentList);
		mav.addObject("canComment", canComment);
		mav.setViewName("movie/detail");
		return mav;
	}

}
