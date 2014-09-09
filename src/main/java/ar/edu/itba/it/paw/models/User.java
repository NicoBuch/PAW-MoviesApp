package ar.edu.itba.it.paw.models;

import java.sql.Date;

public class User extends Entity{
	
	//Agregar Campo identificador para db

	String email;
	String password;
	String firstName;
	String lastName;
	Date birthDate;
	
}
