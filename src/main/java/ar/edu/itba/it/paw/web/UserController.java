package ar.edu.itba.it.paw.web;

import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import ar.edu.itba.it.paw.domain.comment.Comment;
import ar.edu.itba.it.paw.domain.user.LoginFailedException;
import ar.edu.itba.it.paw.domain.user.User;
import ar.edu.itba.it.paw.domain.user.UserRepo;
import ar.edu.itba.it.paw.web.command.SignUpForm;
import ar.edu.itba.it.paw.web.validator.SignUpFormValidator;

@Controller
public class UserController {
	UserRepo users;
	SignUpFormValidator signUpValidator;

	@Autowired
	public UserController(UserRepo users, SignUpFormValidator signUpValidator) {
		this.users = users;
		this.signUpValidator = signUpValidator;
	}

	@RequestMapping(method = RequestMethod.POST)
	public ModelAndView sign_in(
			@RequestParam(value = "email", required = true) String email,
			@RequestParam(value = "password", required = true) String password,
			HttpServletRequest req, HttpServletResponse resp)
			throws IOException {
		HttpSession session = req.getSession();
		ModelAndView mav = new ModelAndView();
		try {
			User us = users.login(email, password);
			session.setAttribute("user_id", us.getId());
			resp.sendRedirect("../movie/index");
		} catch (Exception e) {
			mav.addObject("errorMessage", "Invalid user or password");
		}
		return mav;
	}

	@RequestMapping(method = RequestMethod.GET)
	public ModelAndView sign_in() {
		ModelAndView mav = new ModelAndView();
		return mav;
	}

	@RequestMapping(method = RequestMethod.POST)
	public ModelAndView sign_up(SignUpForm signUpForm, Errors errors) {
		ModelAndView mav = new ModelAndView();

		signUpValidator.validate(signUpForm, errors);
		try {
			users.getByEmail(signUpForm.getEmail());
			errors.rejectValue("email", "taken", "The email is already in use.");
		}catch( Exception e){
			
		}
		
		if (errors.hasErrors()) {
			return null;
		}
		
		users.save(signUpForm.build());	
		mav.setViewName("movie/index");
		return mav;
	}

	@RequestMapping(method = RequestMethod.GET)
	public ModelAndView sign_up() {
		ModelAndView mav = new ModelAndView();
		mav.addObject(new SignUpForm());
		return mav;
	}
	
	@RequestMapping(method = RequestMethod.GET)
	public ModelAndView list(HttpServletRequest req) throws Exception{
		ModelAndView mav = new ModelAndView();
		User logUser = (User) req.getAttribute("user");
		if (logUser == null) {
			throw new Exception();
		}
		
		List<User> listUsers = users.getAll();
		mav.addObject("users", listUsers);
		return mav;
	}

	@RequestMapping(method = RequestMethod.GET)
	public ModelAndView sign_out(HttpServletRequest req,
			HttpServletResponse resp) throws IOException {
		HttpSession session = req.getSession();
		if (session != null) {
			session.invalidate();
		}
		ModelAndView mav = new ModelAndView();
		resp.sendRedirect("../movie/index");
		return mav;
	}

	@RequestMapping(method = RequestMethod.POST)
	public ModelAndView recovery(
			@RequestParam(value = "email", required = true) String email,
			@RequestParam(value = "newPassword", required = false) String newPassword,
			@RequestParam(value = "newPasswordConfirmation", required = false) String newPasswordConfirmation,
			@RequestParam(value = "answer", required = false) String answer,
			HttpServletResponse resp) throws IOException {

		ModelAndView mav = new ModelAndView();
		User user;
		try {
			user = users.getByEmail(email);
			
		}catch(Exception e){
			mav.addObject("errorMessage", "Invalid Email");
			return mav;
		}
		
		mav.addObject("email", email);
		mav.addObject("question", user.getSecretQuestion());
		// Checkear si son correctos
		if (answer != null) {
			if (user.compareAnswer(answer)) {
				if (newPassword.equals(newPasswordConfirmation)) {
					user.setPassword(newPassword);
					resp.sendRedirect("sign_in");
				} else {
					mav.addObject("errorMessage", "Passwords dont match");
					return mav;
				}

			} else {
				mav.addObject("errorMessage", "Invalid Answer");
				return mav;
			}
		}
		return mav;

	}

	@RequestMapping(method = RequestMethod.GET)
	public ModelAndView recovery() {
		ModelAndView mav = new ModelAndView();
		return mav;
	}

	@RequestMapping(method = RequestMethod.GET)
	public ModelAndView comments(@RequestParam(value = "user_id", required = true) User user, HttpServletRequest req) throws Exception {
		User logUser = (User) req.getAttribute("user");
		if (logUser == null) {
			throw new Exception();
		}
		Iterable<Comment> comments = user.getComments();
		ModelAndView mav = new ModelAndView();
		mav.addObject("user", user);
		mav.addObject("comments", comments);
		return mav;
	}
}
