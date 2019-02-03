package edu.northeastern.cs5200.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.sql.SQLException;
import java.util.Map;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Collection;
import java.util.List;


public class PhoneDao extends EstConnDao {
	private final String crtPhone = "INSERT INTO phone (`phone`, `primary`, `personId`) VALUES(?,?,?)";
	private final String updtPhone = "UPDATE phone SET phone.phone = ?, phone.primary = ? WHERE phone.id = ?;";
	private final String delPhone = "Delete FROM phone WHERE phone.id = ?;";
	private final String fndPhoneID =	"SELECT * FROM phone WHERE id = ?";
	private final String fndPhonePerson = "SELECT * FROM phone JOIN person ON phone.personId = person.id WHERE personId = ?";
	private static PhoneDao instance = null;
	private static Map<Integer, Phone> phones = new HashMap<>();
	
	private PhoneDao() {}
	public static PhoneDao getInstance() {
		if (instance == null) {
			instance = new PhoneDao();
		}
		return instance;
	}
	public Phone findPhoneById(int phId) {
		Phone res = null;
		Connection conn = null;
		PreparedStatement statement = null;
		ResultSet result = null;
		try {
			Class.forName(jdbc_drvr);
			conn = DriverManager.getConnection(url, user_name, psswd);
			statement = conn.prepareStatement(fndPhoneID);
			statement.setInt(1, phId);
			result = statement.executeQuery();
			if (result.next()) {
				String phoneNum = result.getString("phone");
				Boolean primary = result.getBoolean("primary");
				res= new Phone(phoneNum, primary);			
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
		return res;
	}
	public Collection<Phone> findPhonesForPerson(int pId) {
		List<Phone> res = new ArrayList<>();
		Connection conn = null;
		PreparedStatement statement = null;
		ResultSet result = null;
		try {
			Class.forName(jdbc_drvr);
			conn = DriverManager.getConnection(url, user_name, psswd);
			statement = conn.prepareStatement(fndPhonePerson);
			statement.setInt(1, pId);
			result = statement.executeQuery();
			while (result.next()) {
				String phoneNum = result.getString("phone");
				Boolean primary = result.getBoolean("primary");
				Phone phone= new Phone(phoneNum, primary);
				res.add(phone);
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
		return res;
	}
	public int deletePhone(int phId) {
		Connection conn = null;
		PreparedStatement statement = null;
		try {
			Class.forName(jdbc_drvr);
			conn = DriverManager.getConnection(url, user_name, psswd);
			statement = conn.prepareStatement(delPhone);
			statement.setInt(1,phId);
			statement.executeUpdate();
			phones.remove(phId);
			statement.close();
			conn.close();
		} 
		catch (ClassNotFoundException e) {
			e.printStackTrace();
		} 
		catch (SQLException e) {
			e.printStackTrace();
		} 
		return 0;
	}
	public int updatePhone(int phId, Phone ph) {
		Connection conn = null;
		PreparedStatement statement = null;
		try {
			Class.forName(jdbc_drvr);
			conn = DriverManager.getConnection(url, user_name, psswd);
			statement = conn.prepareStatement(updtPhone);
			statement.setString(1, ph.getPhone());
			statement.setBoolean(2, ph.isPrimary());
			statement.setInt(3, phId);
			statement.executeUpdate();
			phones.put(phId, ph);
			statement.close();
			conn.close();
		} 
		catch (ClassNotFoundException e) {
			e.printStackTrace();
		} 
		catch (SQLException e) {
			e.printStackTrace();
		} 
		return 0;
	}
	public static Map<Integer, Phone> getPhones() {
		return phones;
	}
	public int createPhone(int pId, Phone ph) {
		Connection conn = null;
		PreparedStatement statement = null;
		ResultSet result = null;
		try {
			Class.forName(jdbc_drvr);
			conn = DriverManager.getConnection(url, user_name, psswd);
			statement = conn.prepareStatement(crtPhone, Statement.RETURN_GENERATED_KEYS);
			statement.setString(1, ph.getPhone());
			statement.setBoolean(2, ph.isPrimary());
			statement.setInt(3, pId);
			statement.executeUpdate();
			result = statement.getGeneratedKeys();
			if(result.next()) {		   
				int id = result.getInt(1);
				phones.put(id, ph);
				return id;
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
		return 0;
	}
}