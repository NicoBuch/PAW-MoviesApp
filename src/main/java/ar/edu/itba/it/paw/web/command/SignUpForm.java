package ar.edu.itba.it.paw.web.command;

import java.sql.Date;

import ar.edu.itba.it.paw.domain.user.User;

public class SignUpForm {

	String firstName;
	String lastName;
	String email;
	//Birth Date
	int birthDay;
	int birthMonth;
	int birthYear;
	/////////////
	String password;
	String confirmPassword;
	String secretQuestion;
	String secretAnswer;


	public SignUpForm() {

	}

	
	public User build(){
		return new User( email, confirmPassword, firstName, lastName, getBirthDate(), secretQuestion, secretAnswer, false);
	}

	public Date getBirthDate(){
		String birthDate = birthYear + "-" + birthMonth + "-" + birthDay;
		return Date.valueOf(birthDate);
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

	public int getBirthDay() {
		return birthDay;
	}

	public void setBirthDay(int birthDay) {
		this.birthDay = birthDay;
	}

	public int getBirthMonth() {
		return birthMonth;
	}

	public void setBirthMonth(int birthMonth) {
		this.birthMonth = birthMonth;
	}

	public int getBirthYear() {
		return birthYear;
	}

	public void setBirthYear(int birthYear) {
		this.birthYear = birthYear;
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
