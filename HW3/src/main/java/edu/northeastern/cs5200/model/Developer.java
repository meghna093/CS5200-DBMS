package edu.northeastern.cs5200.model;

public class Developer extends Person{
	private String developerKey;
	private String phone;
	private String Address;
	
	@Override
	public String toString() {
		return "Developer ["+ super.toString()+"developerKey=" + developerKey + "]";
	}
	public String getDeveloperKey() {
		return developerKey;
	}
	public void setDeveloperKey(String developerKey) {
		this.developerKey = developerKey;
	}
	public Developer(String firstname, String lastname, String username, String password, String email,
			String dob, String developerKey) {
		super(firstname, lastname, username, password, email, dob);
		this.developerKey = developerKey;
	}
	public Developer() {
		super();
	}	
	public Developer(int id, String firstname, String lastname, String username, String password, String email,
			String dob, String developerKey) {
		super(id, firstname, lastname, username, password, email, dob);
		this.developerKey = developerKey;
	}	
	
	public Developer(int id, String firstname, String lastname, String username, String password, String email,
			String developerKey) {
		super(id, firstname, lastname, username, password, email);
		this.developerKey = developerKey;
	}
	public Developer(String firstname, String lastname, String username, String password, String email,String developerKey) {
		super(firstname, lastname, username, password, email);
		this.developerKey = developerKey;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	public String getAddress() {
		return Address;
	}
	public void setAddress(String address) {
		Address = address;
	}
}
