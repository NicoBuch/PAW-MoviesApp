package ar.edu.itba.it.paw.models;

import java.sql.Date;

public class User extends Entity{
	
	String email = "";
	String password;
	String firstName= "";
	String lastName = "";
	Date birthDate;
	
	
	public User(long id, String email, String password, String firstName, String lastName, Date birthDate) {
		super(id);
		setFields(email,password,firstName,lastName,birthDate);
	}
	public User(String email, String password, String firstName, String lastName, Date birthDate) {
		super();
		setFields(email,password,firstName,lastName,birthDate);
		
	}
	public User() {
		super();	
	}
	
	private void setFields(String email, String password, String firstName, String lastName, Date birthDate){
		this.email = email;
		this.password = password;
		this.firstName = firstName;
		this.lastName = lastName;
		this.birthDate = birthDate;
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
