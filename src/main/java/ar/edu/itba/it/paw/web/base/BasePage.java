package ar.edu.itba.it.paw.web.base;

import org.apache.wicket.markup.html.WebPage;
import org.apache.wicket.markup.html.link.Link;
import org.apache.wicket.spring.injection.annot.SpringBean;

import ar.edu.itba.it.paw.domain.EntityResolver;
import ar.edu.itba.it.paw.domain.user.EmailNotFound;
import ar.edu.itba.it.paw.domain.user.User;
import ar.edu.itba.it.paw.domain.user.UserRepo;
import ar.edu.itba.it.paw.web.ConditionalLabel;
import ar.edu.itba.it.paw.web.LoggedLink;
import ar.edu.itba.it.paw.web.MoviesWicketSession;

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
		add(new Link<Void>("homeLink"){
			@Override
			public void onClick(){
				setResponsePage(BasePage.class); //cambiar por la pagina de perfil
			}
		});
		add(new Link<Void>("moviesListLink"){
			@Override
			public void onClick(){
				setResponsePage(BasePage.class); //cambiar por la pagina de peliculas
			}
		});
		add (new LoggedLink("signInLink", false, false ,false , BasePage.class));
		add (new LoggedLink("signOutLink", true, false ,false , BasePage.class));
		add (new LoggedLink("signUpLink", false, false, false, BasePage.class));
		add (new LoggedLink("myProfileLink", true, false , false, BasePage.class));
		add (new LoggedLink("resetPasswordLink", true, false, false, BasePage.class));
		add (new LoggedLink("usersListLink", true, false, false, BasePage.class));
		add (new LoggedLink("reportsListLink", true, true, false, BasePage.class));
		add(new ConditionalLabel("usersName", wicketSession.getEmail(), loggedIn));
	
		}
}
