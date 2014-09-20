package ar.edu.itba.it.paw.models;

import java.sql.Date;

public class User extends Entity {

	private String email;
	private String password;
	private String firstName;
	private String lastName;
	private Date birthDate;
	private String secretQuestion;
	private String secretAnswer;
	private boolean vip;

	public User(String email, String password, String firstName, String lastName, Date birthDate,
			String secretQuestion, String secretAnswer,boolean vip) {
		super();
		setFields(email, password, firstName, lastName, birthDate,
				secretQuestion, secretAnswer, vip);
	}
	public User() {
		super();
	}

	private void setFields(String email, String password, String firstName,
			String lastName, Date birthDate, String secretQuestion,
			String secretAnswer, boolean vip) {
		if (firstName.length() > 255 || lastName.length() > 255
				|| email.length() > 255 || password.length() > 255
				|| (secretQuestion != null && secretQuestion.length() > 255)
				|| (secretAnswer != null && secretAnswer.length() > 255)) {
			throw new IllegalArgumentException();
		}
		this.email = email;
		this.password = password;
		this.firstName = firstName;
		this.lastName = lastName;
		this.birthDate = birthDate;
		this.vip = vip;
		this.secretQuestion = secretQuestion;
		this.secretAnswer = secretAnswer;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
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

	public Date getBirthDate() {
		return birthDate;
	}

	public void setBirthDate(Date birthDate) {
		this.birthDate = birthDate;
	}

	public void setVip(boolean vip) {
		this.vip = vip;
	}
	public boolean isVip(){
		return vip;
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

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (!(obj instanceof User))
			return false;
		User other = (User) obj;
		return this.email.equals(other.email);
	}
}
