package edu.northeastern.cs5200.model;

import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.PreparedStatement;
import java.sql.Connection;

public class PriviledgeDao extends EstConnDao {
	private final String delPagePrivilege = "DELETE FROM `pagepriviledge` WHERE `developerId`=? AND `pageId`=? AND `priviledgeId`=?";
	private final String assgnWebPrivilege = "INSERT INTO `websitepriviledge` (`developerId`, `websiteId`,`priviledgeId`) VALUES (?,?,?)";
	private final String delWebPrivilege = "DELETE FROM `websitepriviledge` WHERE `developerId`=? AND `websiteId`=? AND `priviledgeId`=?";
	private final String assgnPagePrivilege = "INSERT INTO `pagepriviledge` (`developerId`, `pageId`,`priviledgeId`) VALUES (?,?,?)";
	
	private static PriviledgeDao instance = null;
	private PriviledgeDao() { }
	public static PriviledgeDao getInstance() {
	    if(instance == null) {
	        instance = new PriviledgeDao();
	    }
	    return instance;
	}
	public int deletePagePriviledge(int developerId, int pageId, int priviledgeId) {
		int res = -1;
		try {
			Class.forName(jdbc_drvr);
			Connection conn = DriverManager.getConnection(url, user_name, psswd);
			PreparedStatement statement = null;
			statement = conn.prepareStatement(delPagePrivilege);
			statement.setInt(1,developerId);
			statement.setInt(2, pageId);
		    statement.setInt(3, priviledgeId);
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
	public int assignWebsitePriviledge(int developerId, int websiteId, int priviledgeId) {
		int res = -1;
		try {
			Class.forName(jdbc_drvr);
			Connection conn = DriverManager.getConnection(url, user_name, psswd);
			PreparedStatement statement = null;
			statement = conn.prepareStatement(assgnWebPrivilege);
			statement.setInt(1,developerId);
			statement.setInt(2, websiteId);
		    statement.setInt(3, priviledgeId);
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
	public int deleteWebsitePriviledge(int developerId, int websiteId, int priviledgeId) {
		int res = -1;
		try {
			Class.forName(jdbc_drvr);
			Connection conn = DriverManager.getConnection(url, user_name, psswd);
			PreparedStatement statement = null;
			statement = conn.prepareStatement(delWebPrivilege);
			statement.setInt(1,developerId);
			statement.setInt(2, websiteId);
		    statement.setInt(3, priviledgeId);
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
	public int assignPagePriviledge(int developerId, int pageId, int priviledgeId) {
		int res = -1;
		try {
			Class.forName(jdbc_drvr);
			Connection conn = DriverManager.getConnection(url, user_name, psswd);
			PreparedStatement statement = null;
			statement = conn.prepareStatement(assgnPagePrivilege);
			statement.setInt(1,developerId);
			statement.setInt(2, pageId);
		    statement.setInt(3, priviledgeId);
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
}
