package ar.edu.itba.it.paw.web.base;

import org.apache.wicket.Component;
import org.apache.wicket.markup.html.WebPage;
import org.apache.wicket.request.IRequestHandler;
import org.apache.wicket.request.handler.PageProvider;
import org.apache.wicket.request.handler.RenderPageRequestHandler;
import org.apache.wicket.request.mapper.parameter.PageParameters;
import org.apache.wicket.spring.injection.annot.SpringBean;

import ar.edu.itba.it.paw.domain.EntityResolver;
import ar.edu.itba.it.paw.domain.user.User;
import ar.edu.itba.it.paw.domain.user.UserRepo;
import ar.edu.itba.it.paw.web.BaseLink;
import ar.edu.itba.it.paw.web.ConditionalLabel;
import ar.edu.itba.it.paw.web.HomePage;
import ar.edu.itba.it.paw.web.LoggedLink;
import ar.edu.itba.it.paw.web.MoviesWicketSession;
import ar.edu.itba.it.paw.web.comment.ReportedCommentsList;
import ar.edu.itba.it.paw.web.error.ErrorPage;
import ar.edu.itba.it.paw.web.error.RuntimeExceptionHandler;
import ar.edu.itba.it.paw.web.movie.ListMoviesPage;
import ar.edu.itba.it.paw.web.user.ListUsersPage;
import ar.edu.itba.it.paw.web.user.RecoveryPage;
import ar.edu.itba.it.paw.web.user.SignInPage;
import ar.edu.itba.it.paw.web.user.SignUpPage;
import ar.edu.itba.it.paw.web.user.UserDetailPage;

@SuppressWarnings("serial")
public class BasePage extends WebPage {
	@SpringBean
	public UserRepo users;
	@SpringBean private EntityResolver entityResolver;
	MoviesWicketSession wicketSession = MoviesWicketSession.get();
	public User user;
	public BasePage() {
		boolean loggedIn = false;
		if(wicketSession.getEmail() != null){
			loggedIn = true;
		}
		add ( new BaseLink<Void>("homeLink", HomePage.class));
		add ( new BaseLink<Void>("moviesListLink", ListMoviesPage.class));

		add (new LoggedLink<Object>("signInLink", false, false ,false , SignInPage.class, null));
		add (new LoggedLink<Object>("signOutLink", true, false ,false , BasePage.class, null){
			@Override
			public void onClick() {
				wicketSession.signOut();
				setResponsePage(getApplication().getHomePage());
			}
		});
		add (new LoggedLink<Object>("signUpLink", false, false, false, SignUpPage.class, null));
		add (new LoggedLink<Object>("myProfileLink", true, false , false, UserDetailPage.class, null));
		add (new LoggedLink<Object>("resetPasswordLink", true, false, false, RecoveryPage.class, new PageParameters()));
		add (new LoggedLink<Object>("usersListLink", true, false, false, ListUsersPage.class, null));
		add (new LoggedLink<Object>("reportsListLink", true, true, false, ReportedCommentsList.class, null));
		add(new ConditionalLabel("usersName", wicketSession.getEmail(), loggedIn));
	}
}
