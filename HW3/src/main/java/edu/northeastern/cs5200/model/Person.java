package edu.northeastern.cs5200.model;

public class Person {
	private int id;
	private String firstname;
	private String lastname;
	private String username;
	private String password;
	private String email;
	private String dob;
	
	@Override
	public String toString() {
		return "id=" + id + ", firstname=" + firstname + ", lastname=" + lastname + ", username=" + username + ", password=" + password + ", email=" + email + ", ";
	}
	public Person() {
		super();
	}
	public Person(int id, String firstname, String lastname, String username, String password, String email) {
		super();
		this.id = id;
		this.firstname = firstname;
		this.lastname = lastname;
		this.username = username;
		this.password = password;
		this.email = email;
	}
	public Person(String firstname, String lastname, String username, String password, String email) {
		super();
		this.firstname = firstname;
		this.lastname = lastname;
		this.username = username;
		this.password = password;
		this.email = email;
	}
	public Person(int id, String firstname, String lastname, String username, String password, String email, String dob) {
		super();
		this.id = id;
		this.firstname = firstname;
		this.lastname = lastname;
		this.username = username;
		this.password = password;
		this.email = email;
		this.dob = dob;
	}
	public Person(String firstname, String lastname, String username, String password, String email, String dob) {
		super();
		this.firstname = firstname;
		this.lastname = lastname;
		this.username = username;
		this.password = password;
		this.email = email;
		this.dob = dob;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getFirstname() {
		return firstname;
	}
	public void setFirstname(String firstname) {
		this.firstname = firstname;
	}
	public String getLastname() {
		return lastname;
	}
	public String getUsername() {
		return username;
	}
	public void setLastname(String lastname) {
		this.lastname = lastname;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public void setDob(String dob) {
		this.dob = dob;
	}
	public String getDob() {
		return dob;
	}	
}
