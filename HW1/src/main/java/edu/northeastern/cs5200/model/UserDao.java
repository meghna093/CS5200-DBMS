package edu.northeastern.cs5200.model;

import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import com.mysql.jdbc.Statement;
import java.sql.Connection;



public class UserDao extends EstConnDao{
	private final String createPerson = "INSERT INTO person (firstname,lastname,username,password,email,dob) VALUES (?,?,?,?,?,?)";
	private final String createUsers = "INSERT INTO user (id,userKey,userAgreement) VALUES (?,?,?)";
	private final String fetchUsers = "SELECT * FROM person JOIN user ON person.id = user.id";
	private final String fetchUserIds = "SELECT * FROM person JOIN user ON person.id = user.id WHERE user.id=?";
	private final String fetchUserNames = "SELECT * FROM person JOIN user ON person.id = user.id WHERE person.username=?";
	private final String fetchUserCred = "SELECT * FROM person JOIN user ON person.id = user.id WHERE person.username=? AND person.password=?";
	private final String updateUsers = "UPDATE person join user ON person.id = user.id SET firstname=?,lastname=?,username=?,password=?,email=?,dob=?,userAgreement=?,userKey=? WHERE user.id=?";
	private final String deleteUsers = "DELETE from person WHERE id=?";
	
	private UserDao() { }
	private static UserDao instance = null;
	public void dispUsr(ArrayList<User>users) {
		for(User d: users) {
			System.out.println(d);
		}
	}
	public static UserDao getInstance() {
	    if(instance == null) {
	        instance = new UserDao();
	    }
	    return instance;
	}
	public User findUserById(int userId) {
		User usr = new User();
		try {
			Class.forName(jdbc_drvr);
			Connection conn = DriverManager.getConnection(url, user_name, psswd);
			PreparedStatement statement = null;
			statement = conn.prepareStatement(fetchUserIds);
			statement.setInt(1, userId);
			ResultSet res = statement.executeQuery(); 
			while(res.next()) {
				int id=res.getInt("id");
				String firstname = res.getString("firstname");
				String lastname = res.getString("lastname");
				String username = res.getString("username");
				String password = res.getString("password");
				String email = res.getString("email");
				String dob = res.getString("dob");
				String userAgreement = res.getString("userAgreement");
				String userKey = res.getString("userKey");
				usr = new User(id, firstname,lastname,username,password,email,dob,userAgreement,userKey);
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
		return usr;
	}
	public int deleteUser(int userId) {
		int res = -1;
		try {
			Class.forName(jdbc_drvr);
			Connection conn = DriverManager.getConnection(url, user_name, psswd);
			PreparedStatement statement = null;
			statement = conn.prepareStatement(deleteUsers);
			statement.setInt(1,userId);
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
	public User findUserByUsername(String username) {
		User usr = new User();
		try {
			Class.forName(jdbc_drvr);
			Connection conn = DriverManager.getConnection(url, user_name, psswd);
			PreparedStatement statement = null;
			statement = conn.prepareStatement(fetchUserNames);
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
				String userAgreement = res.getString("userAgreement");
				String userKey = res.getString("userKey");
				usr = new User(id, firstname,lastname,user,password,email,dob,userAgreement,userKey);
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
		return usr;
	}
	public int updateUser(int userId, User user) {
		int res = -1;
		try {
			Class.forName(jdbc_drvr);
			Connection conn = DriverManager.getConnection(url, user_name, psswd);
			PreparedStatement statement = null;
			statement = conn.prepareStatement(updateUsers);
			statement.setString(1,user.getFirstname());
			statement.setString(2,user.getLastname());
			statement.setString(3,user.getUsername());
			statement.setString(4,user.getPassword());
			statement.setString(5,user.getEmail());
			statement.setString(6,user.getDob());
			statement.setString(7,user.getUserAgreement());
			statement.setString(8,user.getUserKey());
			statement.setInt(9,userId);
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
	public User findUserByCredentials(String username,String password) {
		User usr = new User();
		try {
			Class.forName(jdbc_drvr);
			Connection conn = DriverManager.getConnection(url, user_name, psswd);
			PreparedStatement statement = null;
			statement = conn.prepareStatement(fetchUserCred);
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
				String userAgreement = res.getString("userAgreement");
				String userKey = res.getString("userKey");
				usr = new User(id, firstname,lastname,user,pass,email,dob,userAgreement,userKey);
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
		return usr;
	}
	public ArrayList<User>findAllUsers(){
		ArrayList<User>usr = new ArrayList<User>();
		try {
			Class.forName(jdbc_drvr);
			Connection conn = DriverManager.getConnection(url, user_name, psswd);
			PreparedStatement statement = null;
			statement = conn.prepareStatement(fetchUsers);
			ResultSet res = statement.executeQuery(); 
			while(res.next()) {
				int id=res.getInt("id");
				String firstname = res.getString("firstname");
				String lastname = res.getString("lastname");
				String username = res.getString("username");
				String password = res.getString("password");
				String email = res.getString("email");
				String dob = res.getString("dob");
				String userAgreement = res.getString("userAgreement");
				String userKey = res.getString("userKey");
				User user = new User(id, firstname,lastname,username,password,email,dob,userAgreement,userKey);
				usr.add(user);
			}
			dispUsr(usr);
			statement.close();
			conn.close();	
		} 
		catch (ClassNotFoundException e) {
			e.printStackTrace();
		} 
		catch (SQLException e) {
			e.printStackTrace();
		}		
		return usr;
	}
	public int createUser(User user) {
		int res = -1;
		try {
			Class.forName(jdbc_drvr);
			Connection conn = DriverManager.getConnection(url, user_name, psswd);
			PreparedStatement statement = null;
			statement = conn.prepareStatement(createPerson,Statement.RETURN_GENERATED_KEYS);
			statement.setString(1, user.getFirstname());
			statement.setString(2, user.getLastname());
			statement.setString(3, user.getUsername());
			statement.setString(4, user.getPassword());
			statement.setString(5, user.getEmail());
			statement.setString(6, user.getDob());
			res = statement.executeUpdate(); 
			ResultSet keys = statement.getGeneratedKeys();
			int usrId;
		    if(keys.next()) {
		      usrId = keys.getInt(1);
		    } 
		    else {
	            throw new SQLException("User ID not returned");
	        }
			statement.close();	
			PreparedStatement statement1 = null;
			statement1 = conn.prepareStatement(createUsers);
			statement1.setInt(1, usrId);
			statement1.setString(2, user.getUserKey());
			statement1.setString(3, user.getUserAgreement());
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
