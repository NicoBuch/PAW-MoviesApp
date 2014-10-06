package ar.edu.itba.it.paw.web;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import ar.edu.itba.it.paw.exceptions.LoginFailedException;
import ar.edu.itba.it.paw.models.User;
import ar.edu.itba.it.paw.services.UserService;

@Controller
public class UserController {
	UserService userService;
	@Autowired
	public UserController(UserService userService){
		this.userService = userService;
	}
	@RequestMapping(method = RequestMethod.POST)
	public ModelAndView sign_in(@RequestParam(value = "email", required = true)String email,
							@RequestParam(value = "password", required = true)String password,
							HttpServletRequest req){
			HttpSession session = req.getSession();
			ModelAndView mav = new ModelAndView();
			try {
				User us = userService.login(email, password);
				session.setAttribute("user", us);
				//Aca deberia hacer un redirect al index!!
				
			} catch (LoginFailedException e) {
				// Preguntar como mostrar los errores en los nuevos formularios
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
	public ModelAndView sign_out(HttpServletRequest req){
		HttpSession session = req.getSession();
		session.setAttribute("user", null);
		ModelAndView mav = new ModelAndView();
		//Ahora deberia hacer un redirect a index!!
		return mav;
	}
}