package ar.edu.itba.it.paw.web.user;

import java.sql.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;

import org.apache.wicket.markup.html.form.DropDownChoice;
import org.apache.wicket.markup.html.form.Form;
import org.apache.wicket.markup.html.form.FormComponent;
import org.apache.wicket.markup.html.form.PasswordTextField;
import org.apache.wicket.markup.html.form.SubmitLink;
import org.apache.wicket.markup.html.form.TextField;
import org.apache.wicket.markup.html.panel.FeedbackPanel;
import org.apache.wicket.model.Model;
import org.apache.wicket.model.PropertyModel;
import org.apache.wicket.spring.injection.annot.SpringBean;
import org.apache.wicket.validation.IValidationError;
import org.apache.wicket.validation.ValidationError;

import ar.edu.itba.it.paw.domain.user.User;
import ar.edu.itba.it.paw.domain.user.UserRepo;
import ar.edu.itba.it.paw.web.BaseLink;
import ar.edu.itba.it.paw.web.HomePage;
import ar.edu.itba.it.paw.web.base.BasePage;

@SuppressWarnings("serial")
public class SignUpPage extends BasePage{
	@SpringBean
	private UserRepo users;

	public SignUpPage(){
		add(new FeedbackPanel("feedback"));
		add(new SignUpForm("signUpForm"));
	}


	private class SignUpForm extends Form<Void>{

		private transient String firstName;
		private transient String lastName;
		private transient String email;
		private transient Integer birthDay;
		private transient Integer birthMonth;
		private transient Integer birthYear;
		private transient String password;
		private transient String confirmPassword;
		private transient String secretQuestion;
		private transient String secretAnswer;


		private TextField<String> firstNameField;
		private TextField<String> lastNameField;
		private TextField<String> emailField;
		private PasswordTextField passwordField;
		private PasswordTextField confirmPasswordField;
		private TextField<String> secretAnswerField;
		private FormComponent<Integer> birthDayField;
		private TextField<String> secretQuestionField;
		private  Pattern rfc2822 = Pattern.compile(
		        "^[a-z0-9!#$%&'*+/=?^_`{|}~-]+(?:\\.[a-z0-9!#$%&'*+/=?^_`{|}~-]+)*@(?:[a-z0-9](?:[a-z0-9-]*[a-z0-9])?\\.)+[a-z0-9](?:[a-z0-9-]*[a-z0-9])?$"
		);

		public SignUpForm(String id) {
			super(id);
			List<Integer> days = new ArrayList<Integer>();
			for(int i = 1; i<= 31; i++){
				days.add(i);
			}
			birthDayField = new DropDownChoice<Integer>("birthDay",new Model<Integer>(), days).setRequired(true);
			add(birthDayField);
			List<Integer> months = new ArrayList<Integer>();
			for(int i = 1; i<= 12; i++){
				months.add(i);
			}
			add(new DropDownChoice<Integer>("birthMonth",new Model<Integer>(), months).setRequired(true));
			List<Integer> years = new ArrayList<Integer>();
			for(int i = 1950; i<= 2014; i++){
				years.add(i);
			}
			add(new DropDownChoice<Integer>("birthYear",new Model<Integer>(), years).setRequired(true));
			addTextField("firstName", true, firstNameField);
			addTextField("lastName", true, lastNameField);
			addTextField("email", true, emailField);
			addPasswordField("password", passwordField);
			addPasswordField("confirmPassword", confirmPasswordField);
			addTextField("secretAnswer", true, secretAnswerField);
			addTextField("secretQuestion", true, secretQuestionField);
			add( new BaseLink<Void>("cancelLink", HomePage.class));
			add( new SubmitLink("signUp"){
				@Override
				public void onSubmit() {
					if (firstName == null) {		//Otra forma mas complicada de setRequired(true)
						firstNameField.error((IValidationError)new ValidationError().addMessageKey("Required"));
					}
					if (lastName == null) {
						lastNameField.error((IValidationError) new ValidationError().addMessageKey("Required"));
					}
					if (email == null) {
						emailField.error((IValidationError) new ValidationError().addMessageKey("Required"));
					}
					else if(!rfc2822.matcher(email).matches()){
						emailField.error((IValidationError) new ValidationError().addMessageKey("invalidEmail"));
					}
					if (password == null) {
						passwordField.error((IValidationError) new ValidationError().addMessageKey("Required"));
					}
					if (confirmPassword == null) {
						confirmPasswordField.error((IValidationError) new ValidationError().addMessageKey("Required"));
					}
					if (secretAnswer == null) {
						secretAnswerField.error((IValidationError) new ValidationError().addMessageKey("Required"));
					}
					if (secretQuestion == null) {
						secretQuestionField.error((IValidationError) new ValidationError().addMessageKey("Required"));
					}
					if(!password.equals(confirmPassword)){
						passwordField.error((IValidationError) new ValidationError().addMessageKey("invalidConfirmation"));
					}
					String birthDateString = birthYear + "-" + birthMonth + "-" + birthDay;
					Date birthDate = Date.valueOf(birthDateString);
					if(! isValidDate(birthDateString) || birthDate.after(new Date(System.currentTimeMillis()))){
						birthDayField.error((IValidationError) new ValidationError().addMessageKey("invalidDate"));
					}

					if (!hasError()) {
						try {
							users.save(new User(email, password, firstName, lastName, birthDate, secretQuestion, secretAnswer, false));
							resetValues();
						} catch ( Exception e) {
							error("invalidRegistration");
						}
					}
				}
			public boolean isValidDate(String inDate) {
			    SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
			    dateFormat.setLenient(false);
			    try {
			      dateFormat.parse(inDate.trim());
			    } catch (ParseException pe) {
			      return false;
			    }
			    return true;
			}
			private void resetValues(){
				firstName = "";
				lastName = "";
				email = "";
				birthDay = 0;
				birthMonth = 0;
				birthYear = 0;
				password = "";
				confirmPassword = "";
				secretQuestion = "";
				secretAnswer = "";
			}
			
		});
	}

		private void addTextField(String id, boolean required, TextField<String> textField){
			textField = new TextField<String>(id,new PropertyModel<String>(this, id));
			textField.setRequired(required);
			add(textField);
		}
		private void addPasswordField(String id, PasswordTextField passwordField){
			passwordField = new PasswordTextField(id,new PropertyModel<String>(this, id));
			passwordField.setRequired(true);
			add(passwordField);
		}

	}
	


}
