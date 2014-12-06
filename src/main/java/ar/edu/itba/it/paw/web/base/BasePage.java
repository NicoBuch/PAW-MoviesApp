package ar.edu.itba.it.paw.web.base;

import org.apache.wicket.markup.html.WebPage;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.link.Link;
import org.apache.wicket.spring.injection.annot.SpringBean;

import ar.edu.itba.it.paw.domain.EntityResolver;
import ar.edu.itba.it.paw.domain.user.EmailNotFound;
import ar.edu.itba.it.paw.domain.user.User;
import ar.edu.itba.it.paw.domain.user.UserRepo;
import ar.edu.itba.it.paw.web.BaseLink;
import ar.edu.itba.it.paw.web.ConditionalLabel;
import ar.edu.itba.it.paw.web.HomePage;
import ar.edu.itba.it.paw.web.LoggedLink;
import ar.edu.itba.it.paw.web.MoviesWicketSession;
import ar.edu.itba.it.paw.web.movie.ListMoviesPage;

@SuppressWarnings("serial")
public class BasePage extends WebPage {
	@SpringBean private UserRepo users;
	@SpringBean private EntityResolver entityResolver;
	User user;
	public BasePage() {
		boolean loggedIn = false;
		MoviesWicketSession wicketSession = MoviesWicketSession.get();
		if(wicketSession.getEmail() != null){
			loggedIn = true;
		}
		add ( new BaseLink<Void>("homeLink", HomePage.class));
		add ( new BaseLink<Void>("moviesListLink", ListMoviesPage.class));

		add (new LoggedLink<Object>("signInLink", false, false ,false , BasePage.class, null));
		add (new LoggedLink<Object>("signOutLink", true, false ,false , BasePage.class, null));
		add (new LoggedLink<Object>("signUpLink", false, false, false, BasePage.class, null));
		add (new LoggedLink<Object>("myProfileLink", true, false , false, BasePage.class, null));
		add (new LoggedLink<Object>("resetPasswordLink", true, false, false, BasePage.class, null));
		add (new LoggedLink<Object>("usersListLink", true, false, false, BasePage.class, null));
		add (new LoggedLink<Object>("reportsListLink", true, true, false, BasePage.class, null));
		add(new ConditionalLabel("usersName", wicketSession.getEmail(), loggedIn));
		}
}
