package edu.northeastern.cs5200.model;

import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.PreparedStatement;
import java.util.ArrayList;
import java.sql.SQLException;
import java.sql.Connection;

import edu.northeastern.cs5200.model.Page;

public class PageDao extends EstConnDao {
	private final String crtPage="INSERT into page (title, description,created,updated,visits, websiteId)" + " VALUES (?,?,curdate(),curdate(),?,?)";
	private final String fndPage="SELECT * FROM page";
	private final String fndPageId="SELECT * FROM page WHERE id=?";
	private final String fndPageTitle="SELECT * FROM page WHERE `title`=? AND `websiteId`=?";
	private final String fndPageWeb="SELECT * FROM page WHERE websiteId=?";
	private final String updtPage="UPDATE page SET title= ? WHERE `id` = ?";
	private final String delPage = "DELETE FROM `page` where `id` = ?";
	private final String recentUpdtPage="SELECT `page`.`id` FROM `page` JOIN `website` " + "ON `page`.`websiteId`=`website`.`id` " + 
			"WHERE `website`.`name`= ? " + "AND `page`.`updated`= (SELECT `x`.`maxdate` " + "FROM (SELECT max(`page`.`updated`) AS `maxdate` " + "FROM `page`) AS `x`)";
	
	private static PageDao instance = null;
	private PageDao() { }
	public void dispPages(ArrayList<Page>pages) {
		for(Page p: pages) {
			System.out.println(p);
		}	
	}
	public static PageDao getInstance() {
	    if(instance == null) {
	        instance = new PageDao();
	    }
	    return instance;
	}
	public Page fndPagesTitle(String title, int webIds){
		Page pg = null;
		try {
			Class.forName(jdbc_drvr);
			Connection conn = DriverManager.getConnection(url, user_name, psswd);
			PreparedStatement statement = null;
			statement = conn.prepareStatement(fndPageTitle);
			statement.setString(1, title);
			statement.setInt(2, webIds);
			ResultSet res = statement.executeQuery(); 
			while(res.next()) {
				int id=res.getInt("id");
				String t = res.getString("title");
				String description = res.getString("description");
				String created = res.getString("created");
				String updated = res.getString("updated");
				int visits = res.getInt("visits");
				int wId = res.getInt("websiteId");
				pg = new Page(id,t,description,created,updated,visits,wId);
				
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
		return pg;
	}
	public int deletePage(int pageId) {
		int res = -1;
		try {
			Class.forName(jdbc_drvr);
			Connection conn = DriverManager.getConnection(url, user_name, psswd);
			PreparedStatement statement = null;
			statement = conn.prepareStatement(delPage);
			statement.setInt(1,pageId);
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
	public int createPageForWebsite(int websiteId, Page page) {
		int res = -1;
		try {
			Class.forName(jdbc_drvr);
			Connection conn = DriverManager.getConnection(url, user_name, psswd);
			PreparedStatement statement = null;
			statement = conn.prepareStatement(crtPage);
			statement.setString(1, page.getTitle());
			statement.setString(2, page.getDescription());
		    statement.setInt(3, page.getVisits());
	    	statement.setInt(4, websiteId);
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
	public int fndRecentUpdtPage(String webName){
		int web_id = -1;
		try {
			Class.forName(jdbc_drvr);
			Connection conn = DriverManager.getConnection(url, user_name, psswd);
			PreparedStatement statement = null;
			statement = conn.prepareStatement(recentUpdtPage);
			statement.setString(1, webName);
			ResultSet res = statement.executeQuery(); 
			while(res.next()) {
				web_id=res.getInt("id");	
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
		return web_id;
	}	
	public Page findPageById(int pageId){
		ArrayList<Page>pgs = new ArrayList<Page>();
		try {
			Class.forName(jdbc_drvr);
			Connection conn = DriverManager.getConnection(url, user_name, psswd);
			PreparedStatement statement = null;
			statement = conn.prepareStatement(fndPageId);
			statement.setInt(1, pageId);
			ResultSet res = statement.executeQuery(); 
			while(res.next()) {
				int id=res.getInt("id");
				String title = res.getString("title");
				String description = res.getString("description");
				String created = res.getString("created");
				String updated = res.getString("updated");
				int visits = res.getInt("visits");
				int websiteId = res.getInt("websiteId");
				Page page = new Page(id,title,description,created,updated,visits,websiteId);
				pgs.add(page);
			}
			dispPages(pgs);
			statement.close();
			conn.close();	
		} 
		catch (ClassNotFoundException e) {
			e.printStackTrace();
		} 
		catch (SQLException e) {
			e.printStackTrace();
		}
		return pgs.get(0);
	}
	public int updatePage(int pageId, Page page) {
		int res = -1;
		try {
			Class.forName(jdbc_drvr);
			Connection conn = DriverManager.getConnection(url, user_name, psswd);
			PreparedStatement statement = null;
			statement = conn.prepareStatement(updtPage);
			statement.setString(1, page.getTitle());
	    	statement.setInt(2, pageId);
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
	public  ArrayList<Page> findPagesForWebsite(int websiteId){
		ArrayList<Page>pgs = new ArrayList<Page>();
		try {
			Class.forName(jdbc_drvr);
			Connection conn = DriverManager.getConnection(url, user_name, psswd);
			PreparedStatement statement = null;
			statement = conn.prepareStatement(fndPageWeb);
			statement.setInt(1, websiteId);
			ResultSet res = statement.executeQuery(); 
			while(res.next()) {
				int id=res.getInt("id");
				String title = res.getString("title");
				String description = res.getString("description");
				String created = res.getString("created");
				String updated = res.getString("updated");
				int visits = res.getInt("visits");
				int webId = res.getInt("websiteId");
				Page page = new Page(id,title,description,created,updated,visits,webId);
				pgs.add(page);
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
		return pgs;
	}
	public ArrayList<Page> findAllPages(){
		ArrayList<Page>pgs = new ArrayList<Page>();
		try {
			Class.forName(jdbc_drvr);
			Connection conn = DriverManager.getConnection(url, user_name, psswd);
			PreparedStatement statement = null;
			statement = conn.prepareStatement(fndPage);
			ResultSet res = statement.executeQuery(); 
			while(res.next()) {
				int id=res.getInt("id");
				String title = res.getString("title");
				String description = res.getString("description");
				String created = res.getString("created");
				String updated = res.getString("updated");
				int visits = res.getInt("visits");
				int websiteId = res.getInt("websiteId");
				Page page = new Page(id,title,description,created,updated,visits,websiteId);
				pgs.add(page);
			}
			dispPages(pgs);
			statement.close();
			conn.close();	
		} 
		catch (ClassNotFoundException e) {
			e.printStackTrace();
		} 
		catch (SQLException e) {
			e.printStackTrace();
		}
		return pgs;
	}	
}
