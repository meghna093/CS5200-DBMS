package edu.northeastern.cs5200.model;

import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.sql.Connection;
import edu.northeastern.cs5200.model.Website;

public class WebsiteDao extends EstConnDao {
	private final String crtWebsite = "INSERT INTO website (name,description,created,updated,visits,developerId) VALUES (?,?,curdate(),curdate(),?,?)";
	private final String fndWebDev = "SELECT * from website WHERE developerId = ?"; 
	private final String fndWebsite = "SELECT * from website";
	private final String delWebsite = "DELETE from website WHERE id=?";
	private final String fndWebId = "SELECT * from website WHERE id = ?"; 
	private final String updtWebsite = "UPDATE website SET name=?, description=?,created=?,updated=?,visits=? WHERE id=?"; 
	private final String fndWebName = "SELECT * from website WHERE name = ?"; 
	
	private static WebsiteDao instance = null;
	private WebsiteDao() { }
	public static WebsiteDao getInstance() {
	    if(instance == null) {
	        instance = new WebsiteDao();
	    }
	    return instance;
	}
	public int deleteWebsite(int websiteId) {
		int res = -1;
		try {
			Class.forName(jdbc_drvr);
			Connection conn = DriverManager.getConnection(url, user_name, psswd);
			PreparedStatement statement = null;
			statement = conn.prepareStatement(delWebsite);
			statement.setInt(1,websiteId);
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
	public void dispWebsites(ArrayList<Website>websites) {
		for(Website w: websites) {
			System.out.println(w);
		}	
	}
	public int updateWebsite(int websiteId, Website website) {
		int res = -1;
		try {
			Class.forName(jdbc_drvr);
			Connection conn = DriverManager.getConnection(url, user_name, psswd);
			PreparedStatement statement = null;
			statement = conn.prepareStatement(updtWebsite);
			statement.setString(1, website.getName());
			statement.setString(2, website.getDescription());
			statement.setString(3, website.getCreated());
			statement.setString(4, website.getUpdated());
		    statement.setInt(5, website.getVisits());
			statement.setInt(6,websiteId);
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
	public int createWebsiteForDeveloper(int developerId, Website website) {	
		int res = -1;
		try {
			Class.forName(jdbc_drvr);
			Connection conn = DriverManager.getConnection(url, user_name, psswd);
			PreparedStatement statement = null;
			statement = conn.prepareStatement(crtWebsite);
			statement.setString(1, website.getName());
			statement.setString(2, website.getDescription());
		    statement.setInt(3, website.getVisits());
	    	statement.setInt(4,developerId);
			res = statement.executeUpdate(); 
			statement.close();
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
	public Website findWebsiteByName(String websiteName){
		Website web = new Website();	
		try {
			Class.forName(jdbc_drvr);
			Connection conn = DriverManager.getConnection(url, user_name, psswd);
			PreparedStatement statement = null;
			statement = conn.prepareStatement(fndWebName);
			statement.setString(1, websiteName);
			ResultSet res = statement.executeQuery(); 
			while(res.next()) {
				int id=res.getInt("id");
				String name = res.getString("name");
				String description = res.getString("description");
				String created = res.getString("created");
				String updated = res.getString("updated");
				int visits = res.getInt("visits");
				int devId = res.getInt("developerId");
				web = new Website(id,name,description,created,updated,visits,devId);
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
		return web;
	}
	public ArrayList<Website>findAllWebsites(){
		ArrayList<Website>web = new ArrayList<Website>();
		try {
			Class.forName(jdbc_drvr);
			Connection conn = DriverManager.getConnection(url, user_name, psswd);
			PreparedStatement statement = null;
			statement = conn.prepareStatement(fndWebsite);
			ResultSet res = statement.executeQuery(); 
			while(res.next()) {
				int id=res.getInt("id");
				String name = res.getString("name");
				String description = res.getString("description");
				String created = res.getString("created");
				String updated = res.getString("updated");
				int visits = res.getInt("visits");
				int developerId = res.getInt("developerId");
				Website website = new Website(id,name,description,created,updated,visits,developerId);
				web.add(website);
			}
			dispWebsites(web);
			statement.close();
			conn.close();	
		} 
		catch (ClassNotFoundException e) {
			e.printStackTrace();
		} 
		catch (SQLException e) {
			e.printStackTrace();
		}
		return web;
	}
	public Website findWebsiteById(int websiteId){
		Website web = new Website();	
		try {
			Class.forName(jdbc_drvr);
			Connection conn = DriverManager.getConnection(url, user_name, psswd);
			PreparedStatement statement = null;
			statement = conn.prepareStatement(fndWebId);
			statement.setInt(1, websiteId);
			ResultSet res = statement.executeQuery(); 
			while(res.next()) {
				int id=res.getInt("id");
				String name = res.getString("name");
				String description = res.getString("description");
				String created = res.getString("created");
				String updated = res.getString("updated");
				int visits = res.getInt("visits");
				int devId = res.getInt("developerId");
				web = new Website(id,name,description,created,updated,visits,devId);
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
		return web;
	}
	public ArrayList<Website>findWebsitesForDeveloper(int developerId){
		ArrayList<Website> webs = new ArrayList<Website>();
		try {
			Class.forName(jdbc_drvr);
			Connection conn = DriverManager.getConnection(url, user_name, psswd);
			PreparedStatement statement = null;
			statement = conn.prepareStatement(fndWebDev);
			statement.setInt(1, developerId);
			ResultSet res = statement.executeQuery(); 
			while(res.next()) {
				int id=res.getInt("id");
				String name = res.getString("name");
				String description = res.getString("description");
				String created = res.getString("created");
				String updated = res.getString("updated");
				int visits = res.getInt("visits");
				int devId = res.getInt("developerId");
				Website website = new Website(id,name,description,created,updated,visits,devId);
				webs.add(website);
			}
			dispWebsites(webs);
			statement.close();
			conn.close();	
		} 
		catch (ClassNotFoundException e) {
			e.printStackTrace();
		} 
		catch (SQLException e) {
			e.printStackTrace();
		}
		return webs;
	}	
}
