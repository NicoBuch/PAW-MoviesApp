package ar.edu.itba.it.paw.web.user;

import org.apache.wicket.markup.html.form.Button;
import org.apache.wicket.markup.html.form.Form;
import org.apache.wicket.markup.html.form.PasswordTextField;
import org.apache.wicket.markup.html.form.TextField;
import org.apache.wicket.markup.html.panel.FeedbackPanel;
import org.apache.wicket.model.CompoundPropertyModel;
import org.apache.wicket.model.ResourceModel;
import org.apache.wicket.spring.injection.annot.SpringBean;

import ar.edu.itba.it.paw.domain.user.EmailNotFound;
import ar.edu.itba.it.paw.domain.user.UserRepo;
import ar.edu.itba.it.paw.web.BaseLink;
import ar.edu.itba.it.paw.web.MoviesWicketSession;
import ar.edu.itba.it.paw.web.base.BasePage;

@SuppressWarnings("serial")
public class SignInPage extends BasePage{

	@SpringBean
	private UserRepo users;

	private transient String email;
	private transient String password;

	public SignInPage() {
		add(new FeedbackPanel("feedback"));
		Form<SignInPage> form = new Form<SignInPage>("loginForm", new CompoundPropertyModel<SignInPage>(this)) {
			@Override
			protected void onSubmit() {
				MoviesWicketSession session = MoviesWicketSession.get();

				try {
					if (session.signIn(email, password, users)) {
						if (!continueToOriginalDestination()) {
							setResponsePage(getApplication().getHomePage());
						}
					} else {
						error(getString("invalidCredentials"));
					}
				} catch (EmailNotFound e) {
					error(getString("invalidCredentials"));
				}
			}
		};
		
		form.add(new TextField<String>("email").setRequired(true));
		form.add(new PasswordTextField("password"));
		form.add(new Button("login", new ResourceModel("login")));
		form.add(new BaseLink<Void>("recoveryLink", RecoveryPage.class));
		add(form);
	}
}
