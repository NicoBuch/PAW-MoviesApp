package ar.edu.itba.it.paw.models;

import java.sql.Date;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "Users")
public class User extends PersistentEntity {

	private String email;
	private String password;
	private String firstName;
	private String lastName;
	private Date birthDate;
	private String secretQuestion;
	private String secretAnswer;
	private boolean vip;

	@OneToMany(mappedBy="user")
	private List<Comment> comments;

	public User(){
	}

	public User(String email, String password, String firstName, String lastName, Date birthDate,
			String secretQuestion, String secretAnswer,boolean vip) {
		super();
		setFields(email, password, firstName, lastName, birthDate,
				secretQuestion, secretAnswer, vip);
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

	public void comment(String body, int rating, Movie movie){
		if(this.canComment(movie)){
			Comment comment = new Comment(body, rating, movie, this);
			comments.add(comment);
			movie.getComments().add(comment);
		}
	}

	public String getEmail() {
		return email;
	}

	public String getPassword() {
		return password;
	}
	public void setPassword(String password){
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

	public boolean isVip(){
		return vip;
	}

	public String getSecretQuestion() {
		return secretQuestion;
	}

	public String getSecretAnswer() {
		return secretAnswer;
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

	public boolean compareAnswer(String answer) {
		return secretAnswer.equals(answer);
	}

	public List<Comment> getComments(){
		return comments;
	}

	public boolean canComment(Movie movie) {
		if((movie.alreadyReleased() || isVip()) && !(alreadyCommented(movie))){
			return true;
		}
		else{
			return false;
		}

	}

	public boolean alreadyCommented(Movie movie){
		for(Comment comment : comments){
			if(comment.getMovie().equals(movie)){
				return true;
			}
		}
		return false;
	}
}
