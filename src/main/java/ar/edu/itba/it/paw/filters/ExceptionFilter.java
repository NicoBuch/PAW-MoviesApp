package ar.edu.itba.it.paw.filters;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;

public class ExceptionFilter implements Filter {

	public void destroy() {
		// TODO Auto-generated method stub
		
	}

	public void doFilter(ServletRequest request, ServletResponse response,
			FilterChain filterChain) throws IOException, ServletException {
		try {
            filterChain.doFilter(request, response);
        } catch ( Throwable ex ) {
            //Forward to...
        	request.setAttribute("exceptionMsg", ex.getMessage());
        	request.getRequestDispatcher("/WEB-INF/jsp/exceptionPage.jsp").forward(request, response);
        	
        }
		
	}

	public void init(FilterConfig arg0) throws ServletException {
		// TODO Auto-generated method stub
		
	}

}
