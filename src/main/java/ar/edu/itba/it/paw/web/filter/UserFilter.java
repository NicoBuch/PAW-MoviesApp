package ar.edu.itba.it.paw.web.filter;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import ar.edu.itba.it.paw.domain.user.UserRepo;

@Component
public class UserFilter extends OncePerRequestFilter {

	private UserRepo users;

	@Autowired
	public UserFilter(UserRepo users) {
		this.users = users;
	}

	@Override
	protected void doFilterInternal(HttpServletRequest request,
			HttpServletResponse response, FilterChain filterChain)
			throws ServletException, IOException {
		try {

			HttpServletRequest req = (HttpServletRequest) request;
			HttpSession session = req.getSession();
			Integer user_id = (Integer) session.getAttribute("user_id");
			if (user_id != null) {
				request.setAttribute("user", users.get(user_id));
			}
			filterChain.doFilter(request, response);

		} catch (Throwable ex) {
			throw new ServletException(ex);
		}
	}

}
