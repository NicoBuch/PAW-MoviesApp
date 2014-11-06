package ar.edu.itba.it.paw.domain.user;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;
import java.util.SortedSet;
import java.util.TreeSet;
import java.util.regex.Pattern;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.hibernate.annotations.Sort;
import org.hibernate.annotations.SortType;

import ar.edu.itba.it.paw.domain.PersistentEntity;
import ar.edu.itba.it.paw.domain.comment.Comment;
import ar.edu.itba.it.paw.domain.commentRating.CommentRating;
import ar.edu.itba.it.paw.domain.movie.Movie;
import ar.edu.itba.it.paw.domain.report.Report;

@Entity
@Table(name = "Users")
public class User extends PersistentEntity {

	private static Pattern rfc2822 = Pattern
			.compile("^[a-z0-9!#$%&'*+/=?^_`{|}~-]+(?:\\.[a-z0-9!#$%&'*+/=?^_`{|}~-]+)*@(?:[a-z0-9](?:[a-z0-9-]*[a-z0-9])?\\.)+[a-z0-9](?:[a-z0-9-]*[a-z0-9])?$");

	private String email;
	private String password;
	private String firstName;
	private String lastName;
	private Date birthDate;
	private String secretQuestion;
	private String secretAnswer;
	private boolean vip;
	private boolean admin;

	@ManyToMany
	@JoinTable(name = "users_of_interest", joinColumns = { @JoinColumn(name = "user_id") }, inverseJoinColumns = { @JoinColumn(name = "user_of_interest_id") })
	private List<User> usersOfInterest;

	@OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
	@Sort(type = SortType.NATURAL)
	private SortedSet<Comment> comments = new TreeSet<Comment>();

	@OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
	private List<CommentRating> commentRatings = new ArrayList<CommentRating>();

	@OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
	private List<Report> reports = new ArrayList<Report>();

	public User() {
	}

	public User(String email, String password, String firstName,
			String lastName, Date birthDate, String secretQuestion,
			String secretAnswer, boolean vip) {
		super();
		setFields(email, password, firstName, lastName, birthDate,
				secretQuestion, secretAnswer, vip);
	}

	private void setFields(String email, String password, String firstName,
			String lastName, Date birthDate, String secretQuestion,
			String secretAnswer, boolean vip) {
		if ((firstName != null && firstName.length() > 255) || (lastName != null && lastName.length() > 255) || email == null
				|| !rfc2822.matcher(email).matches() || email.length() > 255 
				|| password == null || password.length() > 255
				|| (secretQuestion != null && (secretQuestion.length() > 255 || !secretQuestion.endsWith("?")))
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

	public boolean isVip() {
		return vip;
	}

	public String getSecretQuestion() {
		return secretQuestion;
	}

	public String getSecretAnswer() {
		return secretAnswer;
	}

	public boolean isAdmin() {
		return admin;
	}

	public List<User> getUsersOfInterest() {
		return usersOfInterest;
	}

	public void setUsersOfInterest(List<User> usersOfInterest) {
		this.usersOfInterest = usersOfInterest;
	}

	public void setComments(SortedSet<Comment> comments) {
		this.comments = comments;
	}

	public void addComment(Comment comment) {
		comment.setUser(this);
		this.comments.add(comment);
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

	public SortedSet<Comment> getComments() {
		return comments;
	}

	public List<Report> getReports() {
		return reports;
	}

	public boolean canComment(Movie movie) {
		if ((movie.alreadyReleased() || isVip()) && !(alreadyCommented(movie))) {
			return true;
		} else {
			return false;
		}

	}

	public boolean alreadyCommented(Movie movie) {
		for (Comment comment : comments) {
			if (comment.getMovie().equals(movie)) {
				return true;
			}
		}
		return false;
	}

	public boolean canRate(Comment comment) {
		for (CommentRating cr : commentRatings) {
			if (cr.getComment().equals(comment)) {
				return false;
			}
		}
		return true;
	}

	public boolean canReport(Comment comment) {
		for (Report report : reports) {
			if (report.getComment().equals(comment)) {
				return false;
			}
		}
		return true;
	}

	public void rate(CommentRating commentRating) {
		commentRatings.add(commentRating);
		commentRating.setUser(this);
	}

	public void addUserOfInterest(User user_of_interest) {
		usersOfInterest.add(user_of_interest);
	}

	public void removeUserOfInterest(User user_of_interest) {
		usersOfInterest.remove(user_of_interest);

	}

	public void report(Report report) {
		reports.add(report);
		report.setUser(this);

	}
}
