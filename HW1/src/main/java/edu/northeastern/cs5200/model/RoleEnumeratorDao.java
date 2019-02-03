package edu.northeastern.cs5200.model;

import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Connection;

public class RoleEnumeratorDao extends EstConnDao {
	private final String insrtRole = "INSERT INTO `role` (`name`) VALUES (?)";
	private final String fndRoleId = "SELECT `id` FROM `role` WHERE `name`=?";
	private final String insrtPriviledge = "INSERT INTO `priviledge` (`name`) VALUES (?)";
	
	private static RoleEnumeratorDao instance = null;
	private RoleEnumeratorDao() { }
	public static RoleEnumeratorDao getInstance() {
	    if(instance == null) {
	        instance = new RoleEnumeratorDao();
	    }
	    return instance;
	}
	public int fndRoleIds(String role_name){
		int role_id = -1;
		try {
			Class.forName(jdbc_drvr);
			Connection conn = DriverManager.getConnection(url, user_name, psswd);
			PreparedStatement statement = null;
			statement = conn.prepareStatement(fndRoleId);
			statement.setString(1, role_name);
			ResultSet res = statement.executeQuery(); 
			while(res.next()) {
			role_id=res.getInt("id");}
			statement.close();
			conn.close();	
			} 
		catch (ClassNotFoundException e) {
			e.printStackTrace();
		} 
		catch (SQLException e) {
			e.printStackTrace();
			}
		return role_id;
	}
	public int insrtPriviledges(String privi) {
		int res = -1;
		try {
			Class.forName(jdbc_drvr);
			Connection conn = DriverManager.getConnection(url, user_name, psswd);
			PreparedStatement statement = null;
			statement = conn.prepareStatement(insrtPriviledge);
			statement.setString(1,privi);
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
	public int insrtRoles(String role) {
		int res = -1;
		try {
			Class.forName(jdbc_drvr);
			Connection conn = DriverManager.getConnection(url, user_name, psswd);
			PreparedStatement statement = null;
			statement = conn.prepareStatement(insrtRole);
			statement.setString(1,role);
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
