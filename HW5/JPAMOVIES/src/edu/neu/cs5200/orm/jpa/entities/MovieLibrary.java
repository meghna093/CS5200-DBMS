package edu.neu.cs5200.orm.jpa.entities;

import java.util.List;
import java.io.Serializable;
import java.util.ArrayList;
import java.lang.String;
import javax.xml.bind.annotation.XmlRootElement;
import javax.persistence.*;

@Entity
@XmlRootElement
public class MovieLibrary implements Serializable {
	private String name;
	@OneToMany(mappedBy="library", cascade=CascadeType.ALL)
	private List<Movie> movies = new ArrayList();  
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int id;
	private static final long serialVersionUID = 1L;
	public MovieLibrary(String name) {
		this.name = name;
	}
	public MovieLibrary() {
		super();
	}   
	public String getName() {
		return this.name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int getId() {
		return this.id;
	}
	public void setId(int id) {
		this.id = id;
	}   
	public List<Movie> getMovies() {
		return movies;
	}
}
