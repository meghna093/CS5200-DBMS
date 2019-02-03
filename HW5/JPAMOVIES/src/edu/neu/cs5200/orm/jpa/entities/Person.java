package edu.neu.cs5200.orm.jpa.entities;

import java.io.Serializable;
import javax.persistence.Id;
import javax.persistence.InheritanceType;
import javax.persistence.Inheritance;
import javax.persistence.Entity;
import javax.persistence.GenerationType;
import javax.persistence.GeneratedValue;

@Entity
@Inheritance(strategy=InheritanceType.JOINED)
public class Person implements Serializable{ 
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int id;
	private String firstName;
	private String lastName;
	private static final long serialVersionUID = 1L;
	public Person(String fName, String lName) {
		super();
		this.firstName = fName;
		this.lastName = lName;
	}
	public Person() {
		super();
	}  
	public String getFirstName() {
		return this.firstName;
	}
	public void setFirstName(String fName) {
		this.firstName = fName;
	} 
	public int getId() {
		return this.id;
	}
	public void setId(int id) {
		this.id = id;
	}    
	public String getLastName() {
		return this.lastName;
	}
	public void setLastName(String lName) {
		this.lastName = lName;
	}
}
