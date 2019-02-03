package edu.neu.cs5200.orm.jpa.entities;

import java.util.List;
import java.io.Serializable;
import javax.xml.bind.annotation.XmlRootElement;
import javax.persistence.CascadeType;
import javax.persistence.ManyToMany;
import javax.persistence.Entity;
import javax.xml.bind.annotation.XmlElement;

@Entity
@XmlRootElement
public class Actor extends Person implements Serializable {
	private List<Movie> moviesActed;
	private int oscarNominations;
	@ManyToMany(mappedBy="actors", cascade=CascadeType.ALL)
	private static final long serialVersionUID = 1L;
	public Actor(String fName, String lName) {
		super(fName, lName);
	}
	public Actor() {
		super();
	}   
	public List<Movie> getMoviesActed() {
		return moviesActed;
	}
	@XmlElement(name="movie")
	public void setMoviesActed(List<Movie> mves) {
		this.moviesActed = mves;
		for(Movie movie: mves) {
			movie.getActors().add(this);
		}
	}
	public int getOscarNominations() {
		return this.oscarNominations;
	}
	public void setOscarNominations(int oscarNominations) {
		this.oscarNominations = oscarNominations;
	}
}

   

