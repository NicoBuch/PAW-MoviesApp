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
import ar.edu.itba.it.paw.domain.user.NoAdminException;
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
	public String delete(
			@RequestParam(value = "commentId", required = true) Comment comment,
			HttpServletRequest req) throws Exception {
		User user = (User) req.getAttribute("user");
		if (user == null || !user.isAdmin()) {
			throw new NoAdminException();
		}
		Movie movie = comment.getMovie();
		comments.delete(comment);
		return "redirect:../movie/detail?id=" + movie.getId();
	}
	@RequestMapping(method = RequestMethod.POST)
	public String clean(
			@RequestParam(value = "commentId", required = true) Comment comment,
			HttpServletRequest req) throws Exception {
		User user = (User) req.getAttribute("user");
		if (user == null || !user.isAdmin()) {
			throw new NoAdminException();
		}
		comment.cleanReports();
		return "redirect:./list";
	}


	@RequestMapping(method = RequestMethod.POST)
	public ModelAndView create(
			@RequestParam(value = "movieId", required = true) Movie movie,
			@RequestParam(value = "rating", required = true) Integer rating,
			@RequestParam(value = "body", required = true) String body,
			HttpServletRequest req) throws Exception,
			NoMoreThanOneCommentPerUserPerMovieException,
			CantCommentBeforeMoviesReleaseDateException {
		if(body.isEmpty()){
			ModelAndView mav = new ModelAndView("/movie/detail");
			mav.addObject("emptyComment", true);
			return set_detail(mav, movie, true);
		}
		User user = (User) req.getAttribute("user");
		if (user == null || !user.canComment(movie)) {
			throw new Exception();
		}
		if(user.canComment(movie)){
			Comment comment = new Comment(body, rating, movie, user);
			movie.addComment(comment);
			user.addComment(comment);
		}
		return new ModelAndView("redirect:../movie/detail?id=" + movie.getId());
	}

	@RequestMapping(method = RequestMethod.POST)
	public String rate(
			@RequestParam(value = "commentId", required = true) Comment comment,
			@RequestParam(value = "rating", required = true) int rating,
			HttpServletRequest req) throws Exception{
		User user = (User) req.getAttribute("user");
		if(user == null){
			throw new Exception();
		}
		CommentRating commentRating = new CommentRating(user, comment, rating);
		user.rate(commentRating);
		comment.rate(commentRating);
		return "redirect:../movie/detail?id=" + comment.getMovie().getId();
	}

	@RequestMapping(method = RequestMethod.POST)
	public String report(
			@RequestParam( value = "commentId", required = true) Comment comment,
			HttpServletRequest req) throws Exception{
		User user = (User) req.getAttribute("user");
		if(user == null){
			throw new Exception();
		}
		Report report = new Report(user, comment);
		user.report(report);
		comment.addReport(report);
		return "redirect:../movie/detail?id=" + comment.getMovie().getId();
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

	private ModelAndView set_detail(ModelAndView mav, Movie movie, boolean canComment){
		mav.addObject("movie", movie);
		mav.addObject("canComment", canComment);
		return mav;
	}


}
