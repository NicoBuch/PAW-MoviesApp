package ar.edu.itba.it.paw.web.user;

import org.apache.wicket.markup.html.WebMarkupContainer;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.form.Button;
import org.apache.wicket.markup.html.form.EmailTextField;
import org.apache.wicket.markup.html.form.Form;
import org.apache.wicket.markup.html.form.PasswordTextField;
import org.apache.wicket.markup.html.form.TextField;
import org.apache.wicket.markup.html.panel.FeedbackPanel;
import org.apache.wicket.model.CompoundPropertyModel;
import org.apache.wicket.request.mapper.parameter.PageParameters;
import org.apache.wicket.spring.injection.annot.SpringBean;

import ar.edu.itba.it.paw.domain.user.EmailNotFound;
import ar.edu.itba.it.paw.domain.user.User;
import ar.edu.itba.it.paw.domain.user.UserRepo;
import ar.edu.itba.it.paw.web.HomePage;
import ar.edu.itba.it.paw.web.MoviesWicketSession;
import ar.edu.itba.it.paw.web.base.BasePage;

@SuppressWarnings("serial")
public class RecoveryPage extends BasePage{
	@SpringBean UserRepo users;
	private transient String email;
	private transient String question;
	private transient String newPassword;
	private transient String answer;
	private transient String newPasswordConfirmation;
	
	public RecoveryPage(final PageParameters params){
		email = null;
		final EmailTextField emailField = new EmailTextField("email");
		if(params.getNamedKeys().contains("email")){
			email = params.get("email").toString();
		}
		question = null;
		if(params.getNamedKeys().contains("question")){
			question = params.get("question").toString();
		}
		
		add(new FeedbackPanel("feedback"));
		Form<RecoveryPage> recoveryForm = new Form<RecoveryPage>("recoveryForm", new CompoundPropertyModel<RecoveryPage>(this)){
			@Override
			protected void onSubmit() {
				PageParameters params = new PageParameters();
				String question = null;
				try {
					question = users.getByEmail(emailField.getModelObject()).getSecretQuestion();
				} catch (EmailNotFound e) {
					error(getString("invalidEmail"));
					return;
				}
				params.add("email", emailField.getModelObject()).add("question", question);
				setResponsePage(RecoveryPage.class, params);
				
			}
		};
		recoveryForm.setVisible(email == null);
		recoveryForm.add(emailField);
		recoveryForm.add(new Button("nextStepButton"));
		add(recoveryForm);
		
		WebMarkupContainer emailDiv = new WebMarkupContainer("emailDiv");
		emailDiv.setVisible(email != null);
		emailDiv.add(new Label("email", email));
		add(emailDiv);
		PasswordTextField newPasswordField = new PasswordTextField("newPassword");
		PasswordTextField newPasswordConfirmationField = new PasswordTextField("newPasswordConfirmation");
		TextField<String> answerField  = new TextField<String>("answer");

		Form<RecoveryPage> answerForm = new Form<RecoveryPage>("answerForm", new CompoundPropertyModel<RecoveryPage>(this)){
			@Override
			protected void onSubmit() {
				try {
					User user = users.getByEmail(email);
					if(answer.toLowerCase().equals(user.getSecretAnswer().toLowerCase())){
						if(newPassword.equals(newPasswordConfirmation)){
							user.setPassword(newPassword);
							MoviesWicketSession.get().signIn(email, newPassword, users);
							setResponsePage(HomePage.class);
						}
						else{
							error(getString("doesntMatch"));
						}
					}
					else{
						error(getString("invalidAnswer"));
					}
				} catch (EmailNotFound e) {
					error(getString("invalidEmail"));
				}
			}
		};
		answerForm.add(new Label("question", question));
		answerForm.setVisible(question != null);
		answerForm.add(newPasswordField);
		answerForm.add(answerField);
		answerForm.add(newPasswordConfirmationField);
		answerForm.add(new Button("resetButton"));
		add(answerForm);
		
	}
}
