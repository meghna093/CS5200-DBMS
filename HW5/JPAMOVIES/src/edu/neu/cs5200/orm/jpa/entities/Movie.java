package edu.neu.cs5200.orm.jpa.entities;

import java.util.List;
import java.io.Serializable;
import javax.xml.bind.annotation.XmlRootElement;
import javax.persistence.Entity;
import javax.persistence.GenerationType;
import javax.persistence.GeneratedValue;
import javax.persistence.ManyToOne;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.JoinTable;

@Entity
@XmlRootElement
public class Movie implements Serializable {
	private String title;   
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int id;
	@ManyToOne
	private MovieLibrary library;
	@ManyToMany
	@JoinTable(name="Movie2Director")
	private List<Director> directors = null;
	@ManyToMany
	@JoinTable(name="Movie2Actor")
	private List<Actor> actors = null;
	private static final long serialVersionUID = 1L;
	public Movie(String title) {
		super();
		this.title = title;
	}
	public Movie() {
		super();
	}   
	public List<Actor> getActors() {
		return actors;
	}
	public List<Director> getDirectors() {
		return directors;
	}
	public int getId() {
		return this.id;
	}
	public void setId(int id) {
		this.id = id;
	} 
	public MovieLibrary getLibrary() {
		return library;
	}
	public void setLibrary(MovieLibrary lbry) {
		this.library = lbry;
	}
	public String getTitle() {
		return this.title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
}
