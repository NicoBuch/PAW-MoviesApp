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
import ar.edu.itba.it.paw.domain.report.Report;
import ar.edu.itba.it.paw.domain.report.ReportRepo;
import ar.edu.itba.it.paw.domain.user.NoMoreThanOneCommentPerUserPerMovieException;
import ar.edu.itba.it.paw.domain.user.User;

@Controller
public class CommentsController {

	private CommentRepo comments;
	private ReportRepo reports;

	@Autowired
	public CommentsController(CommentRepo comments, ReportRepo reports) {
		this.comments = comments;
		this.reports = reports;
	}

	@RequestMapping(method = RequestMethod.POST)
	public ModelAndView delete(
			@RequestParam(value = "commentId", required = true) Comment comment,
			HttpServletRequest req) throws Exception {
		User user = (User) req.getAttribute("user");
		if (user == null || !user.isAdmin()) {
			throw new Exception();
		}
		Movie movie = comment.getMovie();
		comments.delete(comment);
		return set_detail(movie, user.canComment(movie));
	}
	@RequestMapping(method = RequestMethod.POST)
	public ModelAndView clean(
			@RequestParam(value = "commentId", required = true) Comment comment,
			HttpServletRequest req) throws Exception {
		User user = (User) req.getAttribute("user");
		if (user == null || !user.isAdmin()) {
			throw new Exception();
		}
		reports.deleteByComment(comment);
		ModelAndView mav = new ModelAndView();
		mav.setViewName("comments/list");
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
	
	@RequestMapping(method = RequestMethod.POST)
	public ModelAndView report(
			@RequestParam( value = "commentId", required = true) Comment comment,
			HttpServletRequest req) throws Exception{
		User user = (User) req.getAttribute("user");
		if(user == null){
			throw new Exception();
		}
		comments.report(new Report(user, comment));
		return set_detail(comment.getMovie(), user.canComment(comment.getMovie()));
	}
	
	@RequestMapping(method = RequestMethod.GET)
	public ModelAndView list(
			HttpServletRequest req) throws Exception{
		User user = (User) req.getAttribute("user");
		if(user == null || user.isAdmin() == false){
			throw new Exception();
		}
		ModelAndView mav = new ModelAndView();
		Iterable<Report> reports = comments.getByReports();
		mav.addObject("comments", reports);
		return mav;
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
