package edu.northeastern.cs5200.model;

public class Page {
	private int id;
	private String description;
	private String title;
	private int websiteId;
	private String created;
	private int visits;
	private String updated;
	  
	@Override
	public String toString() {
		return "Page [id=" + id + ", title=" + title + ", description=" + description + ", created=" + created + ", updated=" + updated + ", visits=" + visits + ", websiteId=" + websiteId + "]";
	} 
	public Page() {
		super();
	}
	public Page(String title) {
		super();
		this.title = title;
	}
	public Page(String title, String description, String created, String updated, int visits) {
		super();
		this.title = title;
		this.description = description;
		this.created = created;
		this.updated = updated;
		this.visits = visits;
	}
	public Page(String title, String description, int visits) {
		super();
		this.title = title;
		this.description = description;
		this.visits = visits;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public Page(int id, String title, String description, String created, String updated, int visits, int websiteId) {
		super();
		this.id = id;
		this.title = title;
		this.description = description;
		this.created = created;
		this.updated = updated;
		this.visits = visits;
		this.websiteId = websiteId;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public String getCreated() {
		return created;
	}
	public void setCreated(String created) {
		this.created = created;
	}
	public String getUpdated() {
		return updated;
	}
	public void setUpdated(String updated) {
		this.updated = updated;
	}
	public int getVisits() {
		return visits;
	}
	public void setVisits(int visits) {
		this.visits = visits;
	}
	public int getWebsiteId() {
		return websiteId;
	}
	public void setWebsiteId(int websiteId) {
		this.websiteId = websiteId;
	}	 
}
