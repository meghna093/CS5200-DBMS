package edu.northeastern.cs5200.model;

public class User extends Person{
	private String userAgreement;
	private String userKey;
	
	@Override
	public String toString() {
		return "User [" + super.toString() + " userAgreement=" + userAgreement + ", userKey=" + userKey + "]";
	}
	public User() {
		super();
	}
	public User(String userAgreement, String userKey) {
		super();
		this.userAgreement = userAgreement;
		this.userKey = userKey;
	}
	public User(int id, String firstname, String lastname, String username, String password, String email, String dob,String userAgreement, String userKey) {
		super(id, firstname, lastname, username, password, email, dob);
		this.userAgreement = userAgreement;
		this.userKey = userKey;
	}
	public User(String firstname, String lastname, String username, String password, String email,String userAgreement, String userKey) {
		super(firstname, lastname, username, password, email);
		this.userAgreement = userAgreement;
		this.userKey = userKey;
	}
	public String getUserAgreement() {
		return userAgreement;
	}
	public void setUserAgreement(String userAgreement) {
		this.userAgreement = userAgreement;
	}
	public String getUserKey() {
		return userKey;
	}
	public void setUserKey(String userKey) {
		this.userKey = userKey;
	}	 
}
