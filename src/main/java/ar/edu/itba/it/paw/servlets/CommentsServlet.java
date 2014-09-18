package ar.edu.itba.it.paw.servlets;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import ar.edu.itba.it.paw.models.Comment;
import ar.edu.itba.it.paw.models.User;
import ar.edu.itba.it.paw.services.CommentService;
import ar.edu.itba.it.paw.services.CommentServiceImpl;
import ar.edu.itba.it.paw.services.UserService;
import ar.edu.itba.it.paw.services.UserServiceImpl;

public class CommentsServlet extends HttpServlet{

	private static final long serialVersionUID = 1L;
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		CommentService cs = CommentServiceImpl.getInstance();
		UserService us = UserServiceImpl.getInstance();
		String userId = req.getParameter("user_id");
		if(userId.isEmpty() || userId == null){
			resp.sendRedirect("");
			return;
		}
		User user;
		try{
			user = us.getById(Integer.parseInt(userId));
		}catch (NumberFormatException e){
			resp.sendRedirect("");
			return;
		}
		if(user == null){
			resp.sendRedirect("");
			return;
		}
		Iterable<Comment> comments = cs.getCommentsByUser(user);
		req.setAttribute("comments", comments);
		req.getRequestDispatcher("/WEB-INF/jsp/comments.jsp").forward(req, resp);
		
	}

}
