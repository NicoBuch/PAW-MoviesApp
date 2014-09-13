package ar.edu.itba.it.paw.servlets;
import java.io.IOException;
import java.sql.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.regex.Pattern;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import ar.edu.itba.it.paw.exceptions.EmailAlreadyTakenException;
import ar.edu.itba.it.paw.models.User;
import ar.edu.itba.it.paw.services.UserServiceImpl;

public class Registration extends HttpServlet{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private static final Pattern rfc2822 = Pattern.compile(
	        "^[a-z0-9!#$%&'*+/=?^_`{|}~-]+(?:\\.[a-z0-9!#$%&'*+/=?^_`{|}~-]+)*@(?:[a-z0-9](?:[a-z0-9-]*[a-z0-9])?\\.)+[a-z0-9](?:[a-z0-9-]*[a-z0-9])?$"
	);
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
	throws ServletException, IOException{
		User user = new User();
		
		req.setAttribute("user", user);
		req.setAttribute("error", new Error());
		this.getServletContext().getRequestDispatcher("/WEB-INF/jsp/sign_up.jsp").forward(req, resp);
	}
	
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
	throws ServletException, IOException{
		User user = new User();
		int count = 0;
		String firstName = req.getParameter("firstName");
		String lastName = req.getParameter("lastName");
		String email = req.getParameter("email");
		String password = req.getParameter("password");
		String password_confirmation = req.getParameter("password_confirmation");
		String birthDate = req.getParameter("birthDate");
		if(!firstName.isEmpty()){
			user.setFirstName(firstName);
			count++;
		}
		if(!lastName.isEmpty()){
			user.setLastName(lastName);
			count++;
		}
		if(!email.isEmpty()){
			user.setEmail(email);
			count++;
		}
		if(!birthDate.isEmpty()){
			count++;
		}
		if(password.isEmpty() || count != 4 ){
			req.setAttribute("user", user);
			req.setAttribute("error", new Error("You must complete all the fields to sign up"));
			this.getServletContext().getRequestDispatcher("/WEB-INF/jsp/sign_up.jsp").forward(req, resp);
		}
		if(!password.equals(password_confirmation)){
			req.setAttribute("user", user);
			req.setAttribute("error", new Error("Password and Confirmation doesnt match"));
			this.getServletContext().getRequestDispatcher("/WEB-INF/jsp/sign_up.jsp").forward(req, resp);
		}
		if(!isValidDate(birthDate)){
			req.setAttribute("user", user);
			req.setAttribute("error", new Error("Invalid Date"));
			req.getRequestDispatcher("/WEB-INF/jsp/sign_up.jsp").forward(req, resp);			
		}


		if (!rfc2822.matcher(email).matches()) {
			req.setAttribute("user", user);
			req.setAttribute("error", new Error("Invalid Email"));
			req.getRequestDispatcher("/WEB-INF/jsp/sign_up.jsp").forward(req, resp);  
		}
		
		user.setBirthDate(Date.valueOf(birthDate));
		user.setPassword(password);
		try {
			UserServiceImpl.getInstance().save(user);
		} catch (EmailAlreadyTakenException e) {
			user.setEmail("");
			req.setAttribute("user", user);
			req.setAttribute("error", new Error("Email already in use"));
			req.getRequestDispatcher("/WEB-INF/jsp/sign_up.jsp").forward(req, resp);
		}
	}
	
	
	  public static boolean isValidDate(String inDate) {
		    SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
		    dateFormat.setLenient(false);
		    try {
		      dateFormat.parse(inDate.trim());
		    } catch (ParseException pe) {
		      return false;
		    }
		    return true;
		  }
}

