package ar.edu.itba.it.paw.web.filter;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import ar.edu.itba.it.paw.web.email.EmailSender;

@Component
public class ExceptionFilter extends OncePerRequestFilter {

	 protected void doFilterInternal(HttpServletRequest request,
      HttpServletResponse response, FilterChain filterChain) throws IOException, ServletException {
		try {
            filterChain.doFilter(request, response);
        } catch ( Throwable ex ) {
	       	request.setAttribute("exceptionMsg", ex.getMessage());
	       	request.getRequestDispatcher("/WEB-INF/jsp/exceptionPage.jsp").forward(request, response);
	       	EmailSender emailSender = new EmailSender();
	       	emailSender.sendEmail("Error" + ex.toString(), ex.getMessage());
        	ex.printStackTrace();
        }

	}

}
