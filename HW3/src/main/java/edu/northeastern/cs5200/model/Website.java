package edu.northeastern.cs5200.model;

public class Website {
	private int id;
	private String description;
	private String name;
	private int developerId;
	private String created;
	private int visits;
	private String updated;
	
	@Override
	public String toString() {
		return "Website [id=" + id + ", name=" + name + ", description=" + description + ", created=" + created + ", updated=" + updated + ", visits=" + visits + ", developerId=" + developerId + "]";
	}
	public Website() {
		super();
	}
	public Website(String name, String description, int visits) {
		super();
		this.name = name;
		this.description = description;
		this.visits = visits;
	}
	public Website(String name, String description, int visits,int developerId) {
		super();
		this.name = name;
		this.description = description;
		this.visits = visits;
		this.developerId = developerId;
	}	
	public Website(String name, String description, String created, String updated, int visits) {
		super();
		this.name = name;
		this.description = description;
		this.created = created;
		this.updated = updated;
		this.visits = visits;
	}
	public int getId() {
		return id;
	}
	public Website(int id, String name, String description, String created, String updated, int visits, int developerId) {
		super();
		this.id = id;
		this.name = name;
		this.description = description;
		this.created = created;
		this.updated = updated;
		this.visits = visits;
		this.developerId = developerId;
	}
	public void setId(int id) {
		this.id = id;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getName() {
		return name;
	}
	public String getDescription() {
		return description;
	}
	public String getCreated() {
		return created;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public void setCreated(String created) {
		this.created = created;
	}
	public void setUpdated(String updated) {
		this.updated = updated;
	}
	public String getUpdated() {
		return updated;
	}
	public void setDeveloperId(int developerId) {
		this.developerId = developerId;
	}
	public int getVisits() {
		return visits;
	}
	public int getDeveloperId() {
		return developerId;
	}
	public void setVisits(int visits) {
		this.visits = visits;
	}	
}
