package ar.edu.itba.it.paw.web.secured;

import ar.edu.itba.it.paw.web.MoviesWicketSession;
import ar.edu.itba.it.paw.web.base.BasePage;
import ar.edu.itba.it.paw.web.user.SignInPage;

@SuppressWarnings("serial")
public class SecuredPage extends BasePage{
	public SecuredPage() {
		MoviesWicketSession session = MoviesWicketSession.get();
		if (!session.isSignedIn()) {
			redirectToInterceptPage(new SignInPage());
		}
	}
}
