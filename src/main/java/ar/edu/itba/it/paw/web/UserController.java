package ar.edu.itba.it.paw.web;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import ar.edu.itba.it.paw.exceptions.LoginFailedException;
import ar.edu.itba.it.paw.models.Comment;
import ar.edu.itba.it.paw.models.User;
import ar.edu.itba.it.paw.services.CommentService;
import ar.edu.itba.it.paw.services.UserService;

@Controller
public class UserController {
	UserService userService;
	CommentService commentService;
	@Autowired
	public UserController(UserService userService, CommentService commentService){
		this.userService = userService;
		this.commentService = commentService;
	}
	@RequestMapping(method = RequestMethod.POST)
	public ModelAndView sign_in(@RequestParam(value = "email", required = true)String email,
							@RequestParam(value = "password", required = true)String password,
							HttpServletRequest req,
							HttpServletResponse resp) throws IOException{
			HttpSession session = req.getSession();
			ModelAndView mav = new ModelAndView();
			try {
				User us = userService.login(email, password);
				session.setAttribute("user", us);
				resp.sendRedirect("../movie/index");
				
			} catch (LoginFailedException e) {
				mav.addObject("errorMessage", "Invalid user or password");
			}
		return mav;
	}
	@RequestMapping(method = RequestMethod.GET)
	public ModelAndView sign_in(){
		ModelAndView mav = new ModelAndView();
		return mav;
	}
	@RequestMapping(method = RequestMethod.GET)
	public ModelAndView sign_out(HttpServletRequest req, HttpServletResponse resp) throws IOException{
		HttpSession session = req.getSession();
		session.invalidate();
		ModelAndView mav = new ModelAndView();
		resp.sendRedirect("../movie/index");
		return mav;
	}
	@RequestMapping(method = RequestMethod.POST)
	public ModelAndView recovery(@RequestParam(value = "email", required = true)String email,
							@RequestParam(value = "newPassword", required = false)String newPassword,
							@RequestParam(value = "newPasswordConfirmation", required = false)String newPasswordConfirmation,
							@RequestParam(value = "answer", required = false)String answer,
							HttpServletResponse resp) throws IOException{
		
		ModelAndView mav = new ModelAndView();
		User user = userService.getByEmail(email);
		if(user == null){
			mav.addObject("errorMessage", "Invalid Email");
			return mav;
		}
		else{
			mav.addObject("email", email);
			mav.addObject("question", user.getSecretQuestion());
		}
		//Checkear si son correctos
		if(answer != null){
			if(userService.compareAnswer(user, answer)){
				if(newPassword.equals(newPasswordConfirmation)){
					userService.establishNewPassword(user, newPassword);
					resp.sendRedirect("sign_in");
				}
				else{
					mav.addObject("errorMessage", "Passwords dont match");
					return mav;
				}
				
			}
			else{
				mav.addObject("errorMessage", "Invalid Answer");
				return mav;
			}
		}
		return mav;
		
	}
	@RequestMapping(method = RequestMethod.GET)
	public ModelAndView recovery(){
		ModelAndView mav = new ModelAndView();
		return mav;
	}
	@RequestMapping(method = RequestMethod.GET)
	public ModelAndView comments(HttpServletRequest req) throws Exception{
		User user = (User) req.getSession().getAttribute("user");
		if(user == null){
			throw new Exception();
		}
		ModelAndView mav = new ModelAndView();
		Iterable<Comment> comments = commentService.getCommentsByUser(user);
		req.setAttribute("comments", comments);
		mav.addObject("comments", comments);
		return mav;
	}
}