package ar.edu.itba.it.paw.models;

import java.sql.Date;

public class User extends Entity{
	
	String email = "";
	String password;
	String firstName= "";
	String lastName = "";
	Date birthDate;
	boolean vip;
	
	
	public User(long id, String email, String password, String firstName, String lastName, Date birthDate, boolean vip) {
		super(id);
		setFields(email,password,firstName,lastName,birthDate, vip);
	}
	public User(String email, String password, String firstName, String lastName, Date birthDate, boolean vip) {
		super();
		setFields(email,password,firstName,lastName,birthDate, vip);
		
	}
	public User() {
		super();	
	}
	
	private void setFields(String email, String password, String firstName, String lastName, Date birthDate, boolean vip){

		this.email = email;
		this.password = password;
		this.firstName = firstName;
		this.lastName = lastName;
		this.birthDate = birthDate;
		this.vip = vip;
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
	public void setVip(boolean vip){
		this.vip=vip;
	}
	public boolean getVip(){
		return vip;
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
