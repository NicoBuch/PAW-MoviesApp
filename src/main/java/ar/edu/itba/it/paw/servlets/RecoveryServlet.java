package ar.edu.itba.it.paw.servlets;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import ar.edu.itba.it.paw.models.User;
import ar.edu.itba.it.paw.services.UserService;
import ar.edu.itba.it.paw.services.UserServiceImpl;

@SuppressWarnings("serial")
public class RecoveryServlet extends HttpServlet{

	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException{
		
		
		req.getRequestDispatcher("/WEB-INF/jsp/recovery.jsp").forward(req, resp);
		
	}
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException{
		String email = (String)req.getParameter("email");
		UserService userService = UserServiceImpl.getInstance();
		String newPassword = (String) req.getParameter("newPassword");
		String newPasswordConfirmation = (String) req.getParameter("newPasswordConfirmation");
		
		if(email == null){
			doGet(req, resp);
			return;
		}
		else{
			req.setAttribute("email", email);
		}
		String answer = (String)req.getParameter("answer");
		User user = userService.getByEmail(email);
		if(user == null){
			req.setAttribute("errorMessage", "Invalid Email");
			doGet(req,resp);
			return;
		}
		else{
			req.setAttribute("question", user.getSecretQuestion());
		}
		//Checkear si son correctos
		if(answer != null){
			if(userService.compareAnswer(user, answer)){
				if(newPassword.equals(newPasswordConfirmation)){
					userService.establishNewPassword(user, newPassword);
				}
				else{
					req.setAttribute("errorMessage", "Passwords dont match");
					doGet(req,resp);
					return;
				}
				
			}
			else{
				req.setAttribute("errorMessage", "Invalid Answer");
				doGet(req,resp);
				return;
			}
		}
		//Programacion defensiva
		else{
			doGet(req,resp);
			return;
		}
		resp.sendRedirect("sign_in");
		
	}
}
