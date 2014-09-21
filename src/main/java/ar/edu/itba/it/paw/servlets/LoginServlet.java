package ar.edu.itba.it.paw.servlets;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import ar.edu.itba.it.paw.exceptions.LoginFailedException;
import ar.edu.itba.it.paw.models.User;
import ar.edu.itba.it.paw.services.UserService;
import ar.edu.itba.it.paw.services.UserServiceImpl;

@SuppressWarnings("serial")
public class LoginServlet extends HttpServlet{
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException{
		resp.setContentType("text/html");
		User user = (User) req.getAttribute("user");
		if(user != null){
			resp.sendRedirect("");
		}
		req.getRequestDispatcher("/WEB-INF/jsp/login.jsp").forward(req, resp);
	}
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException{
		HttpSession session = req.getSession();
		User user = (User) session.getAttribute("user");
		if(user != null){
			return;
		}
		String email = req.getParameter("email");
		String password = req.getParameter("password");
		UserService userService = UserServiceImpl.getInstance();
		try {
			User us = userService.login(email, password);
			session.setAttribute("user", us );
			resp.sendRedirect("");
		} catch (LoginFailedException e) {
			// TODO Auto-generated catch block
				req.setAttribute("email", email);
				req.setAttribute("errorMessage", "Invalid user or password");
				req.getRequestDispatcher("/WEB-INF/jsp/login.jsp").forward(req, resp);
				e.printStackTrace();
		}
	}
}
