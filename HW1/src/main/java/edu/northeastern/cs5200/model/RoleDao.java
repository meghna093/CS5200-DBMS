package edu.northeastern.cs5200.model;

import java.sql.DriverManager;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import edu.northeastern.cs5200.model.Role;

public class RoleDao extends EstConnDao{
	private final String assgnWebRole = "INSERT INTO `websiterole` (`developerId`, `websiteId`,`roleId`) VALUES (?,?,?)";
	private final String delWebRole = "DELETE FROM `websiterole` WHERE `developerId`=? AND `websiteId`=? AND `roleId`=?";
	private final String assgnPageRole = "INSERT INTO `pagerole` (`developerId`, `pageId`,`roleId`) VALUES (?,?,?)";
	private final String delPageRole = "DELETE FROM `pagerole` WHERE `developerId`=? AND `pageId`=? AND `roleId`=?";
	private final String switchRole = "UPDATE `pagerole` AS `p1`, `pagerole` AS `p2` SET `p1`.`roleId` = `p2`.`roleId`,`p2`.`roleId`= ? WHERE (`p1`.`id`,`p2`.`id`)=(?,?)";
	private final String fndRoleId = "SELECT `pagerole`.`id`,`pagerole`.`roleId` FROM `pagerole`,`developer`,`page`,`person` WHERE `pagerole`.`developerId`=`developer`.`id`" + 
			" AND `pagerole`.`pageId`=`page`.`id` AND `developer`.`id`=`person`.`id` AND `developer`.`id`= ? AND `page`.`id`= ?";
	
	private static RoleDao instance = null;
	private RoleDao() { }
	public static RoleDao getInstance() {
	    if(instance == null) {
	        instance = new RoleDao();
	    }
	    return instance;
	}
	public Role fndRoleIds(int devId, int pgId){
		Role role = new Role();
		try {
		Class.forName(jdbc_drvr);
		Connection conn = DriverManager.getConnection(url, user_name, psswd);
		PreparedStatement statement = null;
		statement = conn.prepareStatement(fndRoleId);
		statement.setInt(1, devId);
		statement.setInt(2, pgId);
		ResultSet res = statement.executeQuery(); 
		while(res.next()) {
			int id=res.getInt("id");
			int roleId = res.getInt("roleId");
			role = new Role(id,roleId);
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
		return role;
	}
	public int deletePageRole(int developerId, int pageId, int roleId) {
		int res = -1;
		try {
			Class.forName(jdbc_drvr);
			Connection conn = DriverManager.getConnection(url, user_name, psswd);
			PreparedStatement statement = null;
			statement = conn.prepareStatement(delPageRole);
			statement.setInt(1,developerId);
			statement.setInt(2, pageId);
		    statement.setInt(3, roleId);
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
	public int assignWebsiteRole(int developerId, int websiteId, int roleId) {
		int res = -1;
		try {
			Class.forName(jdbc_drvr);
			Connection conn = DriverManager.getConnection(url, user_name, psswd);
			PreparedStatement statement = null;
			statement = conn.prepareStatement(assgnWebRole);
			statement.setInt(1,developerId);
			statement.setInt(2, websiteId);
		    statement.setInt(3, roleId);
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
	public int switchRoles(int dev_Id1, int dev_Id2, int pg_Id) {
		int res = -1;
		int r1 = fndRoleIds(dev_Id1,pg_Id).getRoleId();
		int dev_id1 = fndRoleIds(dev_Id1,pg_Id).getId();
		int dev_id2 = fndRoleIds(dev_Id2,pg_Id).getId();
		try {
			Class.forName(jdbc_drvr);
			Connection conn = DriverManager.getConnection(url, user_name, psswd);
			PreparedStatement statement = null;
			statement = conn.prepareStatement(switchRole);
	    	statement.setInt(1, r1);
	    	statement.setInt(2, dev_id1);
	    	statement.setInt(3, dev_id2);
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
	public int assignPageRole(int developerId, int pageId, int roleId) {
		int res = -1;
		try {
			Class.forName(jdbc_drvr);
			Connection conn = DriverManager.getConnection(url, user_name, psswd);
			PreparedStatement statement = null;
			statement = conn.prepareStatement(assgnPageRole);
			statement.setInt(1,developerId);
			statement.setInt(2, pageId);
		    statement.setInt(3, roleId);
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
	public int deleteWebsiteRole(int developerId, int websiteId, int roleId) {
		int res = -1;
		try {
			Class.forName(jdbc_drvr);
			Connection conn = DriverManager.getConnection(url, user_name, psswd);
			PreparedStatement statement = null;
			statement = conn.prepareStatement(delWebRole);
			statement.setInt(1,developerId);
			statement.setInt(2, websiteId);
		    statement.setInt(3, roleId);
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
}
