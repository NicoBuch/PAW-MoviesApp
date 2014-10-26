package ar.edu.itba.it.paw.command;

public class SignUpForm {
	
	String firstName;
	String lastName;
	String email;
	//Birth Date
	String day;
	String month;
	String year;
	/////////////
	String password;
	String confirmPassword;
	String secretQuestion;
	String secretAnswer;
	
	public SignUpForm(String firstName, String lastName, String email,
			String day, String month, String year, String password,
			String confirmPassword, String secretQuestion, String secretAnswer) {
		super();
		this.firstName = firstName;
		this.lastName = lastName;
		this.email = email;
		this.day = day;
		this.month = month;
		this.year = year;
		this.password = password;
		this.confirmPassword = confirmPassword;
		this.secretQuestion = secretQuestion;
		this.secretAnswer = secretAnswer;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getDay() {
		return day;
	}

	public void setDay(String day) {
		this.day = day;
	}

	public String getMonth() {
		return month;
	}

	public void setMonth(String month) {
		this.month = month;
	}

	public String getYear() {
		return year;
	}

	public void setYear(String year) {
		this.year = year;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getConfirmPassword() {
		return confirmPassword;
	}

	public void setConfirmPassword(String confirmPassword) {
		this.confirmPassword = confirmPassword;
	}

	public String getSecretQuestion() {
		return secretQuestion;
	}

	public void setSecretQuestion(String secretQuestion) {
		this.secretQuestion = secretQuestion;
	}

	public String getSecretAnswer() {
		return secretAnswer;
	}

	public void setSecretAnswer(String secretAnswer) {
		this.secretAnswer = secretAnswer;
	}
	
	
	
	
	
}
