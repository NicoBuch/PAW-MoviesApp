package ar.edu.itba.it.paw.servlets;
import java.io.IOException;
import java.sql.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
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
		
		req.setAttribute("error", new Error());
		this.getServletContext().getRequestDispatcher("/WEB-INF/jsp/sign_up.jsp").forward(req, resp);
	}

	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
	throws ServletException, IOException{
		int count = 0;
		String bDay = req.getParameter("bDay");
		String bMonth = req.getParameter("bMonth");
		String bYear = req.getParameter("bYear");
		String firstName = req.getParameter("firstName");
		String lastName = req.getParameter("lastName");
		String email = req.getParameter("email");
		String password = req.getParameter("password");
		String password_confirmation = req.getParameter("passwordConfirmation");
		String secretQuestion = req.getParameter("secretQuestion");
		String secretAnswer = req.getParameter("secretAnswer");
		String birthDate = "";
		List<Error> errors = new ArrayList<Error>();
		if(!firstName.isEmpty()){
			req.setAttribute("firstName",firstName);
			count++;
		}
		if(!lastName.isEmpty()){
			req.setAttribute("lastName",lastName);
			count++;
		}
		if(!email.isEmpty()){
			req.setAttribute("email",email);
			count++;
		}
		if(bDay!=null && !bDay.isEmpty()){
			req.setAttribute("bDay",bDay);
			count++;
		}
		if(bYear!=null  && !bYear.isEmpty()){
			req.setAttribute("bYear",bYear);
			count++;
		}
		if(bMonth!=null && !bMonth.isEmpty()){
			req.setAttribute("bMonth",bMonth);
			count++;
		}
		if(!secretQuestion.isEmpty()){
			req.setAttribute("secretQuestion",secretQuestion);
		}
		if(count != 6){
			errors.add(new Error("You must fill all the fields except the question ones"));
		}
		else{
			birthDate = bYear + "-" + bMonth + "-" + bDay;
		}
		if(!password.equals(password_confirmation)){
			errors.add(new Error("Password and Confirmation doesnt match"));
		}
		if(password.isEmpty()){
			errors.add(new Error("Password cant be empty"));
		}
		if(!birthDate.isEmpty()){
			if(! isValidDate(birthDate)){
				errors.add(new Error("Invalid Date"));
			}
			else if(Date.valueOf(birthDate).after(new Date(System.currentTimeMillis()))){
				errors.add(new Error("Invalid Date"));
			}
		}

		if (!rfc2822.matcher(email).matches()) {
			errors.add(new Error("Invalid Email"));
		}
		if(! (secretQuestion.isEmpty() || secretQuestion.endsWith("?"))){
		errors.add(new Error("Question must end with \"?\" "));
		}
		if(!secretQuestion.isEmpty() && secretAnswer.isEmpty()){
			errors.add(new Error("The answer to your question cant be empty"));
		}
		if (firstName.length() > 255 || lastName.length() > 255
				|| email.length() > 255 || password.length() > 255
				|| (secretQuestion != null && secretQuestion.length() > 255)
				|| (secretAnswer != null && secretAnswer.length() > 255)) {
			errors.add(new Error("Fields must have less than 256 characters"));
		}
		if(!errors.isEmpty()){
			req.setAttribute("errors", errors);
			req.getRequestDispatcher("/WEB-INF/jsp/sign_up.jsp").forward(req, resp);
			return;
		}
		try {
			User user = new User(email, password, firstName, lastName, Date.valueOf(birthDate), secretQuestion, secretAnswer, false);
			UserServiceImpl.getInstance().save(user);
			
			resp.sendRedirect("sign_in");
		} catch (EmailAlreadyTakenException e) {
			errors.add(new Error("Email already in use"));
			req.setAttribute("errors", errors);
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

