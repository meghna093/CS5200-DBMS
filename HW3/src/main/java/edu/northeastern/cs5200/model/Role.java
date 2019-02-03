package edu.northeastern.cs5200.model;
	
public class Role {
	private int id;
	private int developerId;
	private int pageOrWebsiteId;
	private int roleId;	
	
	public Role() {
		super();
	}
	public Role(int developerId, int pageWebsiteId, int roleId) {
		super();
		this.developerId = developerId;
		this.pageOrWebsiteId = pageWebsiteId;
		this.roleId = roleId;
	}
	public Role(int id, int roleId) {
		super();
		this.id = id;
		this.roleId = roleId;
	}
	public Role(int id, int developerId, int pageOrWebsiteId, int roleId) {
		super();
		this.id = id;
		this.developerId = developerId;
		this.pageOrWebsiteId = pageOrWebsiteId;
		this.roleId = roleId;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getDeveloperId() {
		return developerId;
	}
	public void setDeveloperId(int developerId) {
		this.developerId = developerId;
	}
	public int getPageWebsiteId() {
		return pageOrWebsiteId;
	}
	public void setPageWebsiteId(int pageOrWebsiteId) {
		this.pageOrWebsiteId = pageOrWebsiteId;
	}
	public int getRoleId() {
		return roleId;
	}
	public void setRoleId(int roleId) {
		this.roleId = roleId;
	}
}
