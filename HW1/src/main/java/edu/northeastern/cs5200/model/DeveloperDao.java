package edu.northeastern.cs5200.model;

import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import com.mysql.jdbc.Statement;
import java.sql.Connection;

public class DeveloperDao extends EstConnDao{
	private final String createPerson = "INSERT INTO person (firstname,lastname,username,password,email,dob) VALUES (?,?,?,?,?,?)";
	private final String createDevelopers = "INSERT INTO developer (id,developerKey) VALUES (?,?)";
	private final String fetchDevelopers = "SELECT * FROM person JOIN developer ON person.id = developer.id";
	private final String fetchDeveloperIds = "SELECT * FROM person JOIN developer ON person.id = developer.id WHERE developer.id=?";
	private final String fetchDeveloperNames = "SELECT * FROM person JOIN developer ON person.id = developer.id WHERE person.username=?";
	private final String fetchDeveloperCred = "SELECT * FROM person JOIN developer ON person.id = developer.id WHERE person.username=? AND person.password=?";
	private final String updateDevelopers = "UPDATE person join developer ON person.id = developer.id SET firstname=?,lastname=?,username=?, password=?,email=?,dob=?,developerKey=? WHERE developer.id=?";
	private final String deleteDevelopers = "DELETE from person WHERE id=?";
	
	private DeveloperDao() { }
	private static DeveloperDao instance = null;
	public void dispDev(ArrayList<Developer>developers) {
		for(Developer d: developers) {
			System.out.println(d);
		}
	}
	public static DeveloperDao getInstance() {
	    if(instance == null) {
	        instance = new DeveloperDao();
	    }
	    return instance;
	}
	public Developer findDeveloperById(int developerId) {
		Developer dev = new Developer();
		try {
			Class.forName(jdbc_drvr);
			Connection conn = DriverManager.getConnection(url, user_name, psswd);
			PreparedStatement statement = null;
			statement = conn.prepareStatement(fetchDeveloperIds);
			statement.setInt(1, developerId);
			ResultSet res = statement.executeQuery(); 
			while(res.next()) {
				int id=res.getInt("id");
				String firstname = res.getString("firstname");
				String lastname = res.getString("lastname");
				String username = res.getString("username");
				String password = res.getString("password");
				String email = res.getString("email");
				String dob = res.getString("dob");
				String developerKey = res.getString("developerKey");
				dev = new Developer(id, firstname,lastname,username,password,email,dob,developerKey);
			}
			statement.close();
			conn.close();	
		} 
		catch (ClassNotFoundException e) {
			e.printStackTrace();
		} 
		catch (SQLException e) {
			e.printStackTrace();
		}	
		return dev;
	}
	public int deleteDeveloper(int developerId) {
		int res = -1;
		try {
			Class.forName(jdbc_drvr);
			Connection conn = DriverManager.getConnection(url, user_name, psswd);
			PreparedStatement statement = null;
			statement = conn.prepareStatement(deleteDevelopers);
			statement.setInt(1,developerId);
			res = statement.executeUpdate(); 
			statement.close();
			conn.close();
		} 
		catch (ClassNotFoundException e) {
			e.printStackTrace();
		} 
		catch (SQLException e) {
			e.printStackTrace();
		}	
		return res;
	}
	public Developer findDeveloperByUsername(String username) {
		Developer dev = new Developer();
		try {
			Class.forName(jdbc_drvr);
			Connection conn = DriverManager.getConnection(url, user_name, psswd);
			PreparedStatement statement = null;
			statement = conn.prepareStatement(fetchDeveloperNames);
			statement.setString(1, username);
			ResultSet res = statement.executeQuery(); 
			while(res.next()) {
				int id=res.getInt("id");
				String firstname = res.getString("firstname");
				String lastname = res.getString("lastname");
				String user = res.getString("username");
				String password = res.getString("password");
				String email = res.getString("email");
				String dob = res.getString("dob");
				String developerKey = res.getString("developerKey");
				dev = new Developer(id, firstname,lastname,user,password,email,dob,developerKey);
			}
			statement.close();
			conn.close();	
		} 
		catch (ClassNotFoundException e) {
			e.printStackTrace();
		} 
		catch (SQLException e) {
			e.printStackTrace();
		}
		return dev;
	}
	public int updateDeveloper(int developerId, Developer developer) {
		int res = -1;
		try {
			Class.forName(jdbc_drvr);
			Connection conn = DriverManager.getConnection(url, user_name, psswd);
			PreparedStatement statement = null;
			statement = conn.prepareStatement(updateDevelopers);
			statement.setString(1,developer.getFirstname());
			statement.setString(2,developer.getLastname());
			statement.setString(3,developer.getUsername());
			statement.setString(4,developer.getPassword());
			statement.setString(5,developer.getEmail());
			statement.setString(6,developer.getDob());
			statement.setString(7,developer.getDeveloperKey());
			statement.setInt(8,developerId);
			res = statement.executeUpdate(); 
			statement.close();
			conn.close();
		} 
		catch (ClassNotFoundException e) {
			e.printStackTrace();
		} 
		catch (SQLException e) {
			e.printStackTrace();
		}	
		return res;
	}
	public Developer findDeveloperByCredentials(String username,String password) {
		Developer dev = new Developer();
		try {
			Class.forName(jdbc_drvr);
			Connection conn = DriverManager.getConnection(url, user_name, psswd);
			PreparedStatement statement = null;
			statement = conn.prepareStatement(fetchDeveloperCred);
			statement.setString(1, username);
			statement.setString(2, password);
			ResultSet res = statement.executeQuery(); 
			while(res.next()) {
				int id=res.getInt("id");
				String firstname = res.getString("firstname");
				String lastname = res.getString("lastname");
				String user = res.getString("username");
				String pass = res.getString("password");
				String email = res.getString("email");
				String dob = res.getString("dob");
				String developerKey = res.getString("developerKey");
				dev = new Developer(id, firstname,lastname,user,pass,email,dob,developerKey);
			}
			statement.close();
			conn.close();	
		} 
		catch (ClassNotFoundException e) {
			e.printStackTrace();
		} 
		catch (SQLException e) {
			e.printStackTrace();
		}
		return dev;
	}
	public ArrayList<Developer>findAllDevelopers(){
		ArrayList<Developer>dev = new ArrayList<Developer>();
		try {
			Class.forName(jdbc_drvr);
			Connection conn = DriverManager.getConnection(url, user_name, psswd);
			PreparedStatement statement = null;
			statement = conn.prepareStatement(fetchDevelopers);
			ResultSet res = statement.executeQuery(); 
			while(res.next()) {
				int id=res.getInt("id");
				String firstname = res.getString("firstname");
				String lastname = res.getString("lastname");
				String username = res.getString("username");
				String password = res.getString("password");
				String email = res.getString("email");
				String dob = res.getString("dob");
				String developerKey = res.getString("developerKey");
				Developer developer = new Developer(id, firstname,lastname,username,password,email,dob,developerKey);
				dev.add(developer);
			}
			dispDev(dev);
			statement.close();
			conn.close();	
		} 
		catch (ClassNotFoundException e) {
			e.printStackTrace();
		} 
		catch (SQLException e) {
			e.printStackTrace();
		}		
		return dev;
	}
	public int createDeveloper(Developer developer) {
		int res = -1;
		try {
			Class.forName(jdbc_drvr);
			Connection conn = DriverManager.getConnection(url, user_name, psswd);
			PreparedStatement statement = null;
			statement = conn.prepareStatement(createPerson,Statement.RETURN_GENERATED_KEYS);
			statement.setString(1, developer.getFirstname());
			statement.setString(2, developer.getLastname());
			statement.setString(3, developer.getUsername());
			statement.setString(4, developer.getPassword());
			statement.setString(5, developer.getEmail());
			statement.setString(6, developer.getDob());
			res = statement.executeUpdate(); 
			ResultSet keys = statement.getGeneratedKeys();
			int devId;
		    if(keys.next()) {
		      devId = keys.getInt(1);
		    } 
		    else {
	            throw new SQLException("Developer ID not returned");
	        }
			statement.close();	
			PreparedStatement statement1 = null;
			statement1 = conn.prepareStatement(createDevelopers);
			statement1.setInt(1, devId);
			statement1.setString(2, developer.getDeveloperKey());
			res = statement1.executeUpdate(); 
			statement1.close();
			conn.close();
			return res;
		} 
		catch (ClassNotFoundException e) {
			e.printStackTrace();
		} 
		catch (SQLException e) {
			e.printStackTrace();
		}		
		return res;
	}	
}
