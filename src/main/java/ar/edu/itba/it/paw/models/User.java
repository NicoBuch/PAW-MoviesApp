package ar.edu.itba.it.paw.models;

import java.sql.Date;

public class User extends Entity{
	
	//Agregar Campo identificador para db

	String email;
	String password;
	String firstName;
	String lastName;
	Date birthDate;
	
	public User(long id, String email, String password, String firstName, String lastName, Date birthDate) {
		super(id);
		setFields(email,password,firstName,lastName,birthDate);
	}
	public User(String email, String password, String firstName, String lastName, Date birthDate) {
		super();
		setFields(email,password,firstName,lastName,birthDate);
		
	}
	private void setFields(String email, String password, String firstName, String lastName, Date birthDate){
		this.email = email;
		this.password = password;
		this.firstName = firstName;
		this.lastName = lastName;
		this.birthDate = birthDate;
	}
	
}
