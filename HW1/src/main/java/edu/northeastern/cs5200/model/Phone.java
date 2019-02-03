package edu.northeastern.cs5200.model;

public class Phone {
	private int id;
	private String phone;
	private boolean primary;
	
	public Phone(){};
	public Phone(String phone, boolean primary) {
		this.phone = phone;
		this.primary = primary;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public boolean isPrimary() {
		return primary;
	}
	public void setPrimary(boolean primary) {
		this.primary = primary;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
}
