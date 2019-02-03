package edu.neu.cs5200.orm.jpa.entities;

import java.util.List;
import java.io.Serializable;
import javax.xml.bind.annotation.XmlRootElement;
import javax.persistence.*;

@Entity
@XmlRootElement
public class Director extends Person implements Serializable  {
	private int oscarWins;
	private List<Movie> moviesDirected;
	@ManyToMany(mappedBy="directors", cascade=CascadeType.ALL)
	private static final long serialVersionUID = 1L;
	public Director(String fName, String lName) {
		super(fName, lName);
	}
	public Director() {
		super();
	}   
	public int getOscarWins() {
		return this.oscarWins;
	}
	public void setOscarWins(int osWins) {
		this.oscarWins = osWins;
	}
	public List<Movie> getMoviesDirected() {
		return this.moviesDirected;
	}
}
