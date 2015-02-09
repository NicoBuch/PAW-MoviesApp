package ar.edu.itba.it.paw.web.secured;

import ar.edu.itba.it.paw.web.MoviesWicketSession;
import ar.edu.itba.it.paw.web.user.SignInPage;

@SuppressWarnings("serial")
public class AdminPage extends SecuredPage{
	
	protected AdminPage(){
		MoviesWicketSession session = MoviesWicketSession.get();
		if(!session.isAdmin()){
			redirectToInterceptPage(new SignInPage());
		}
	}
}
