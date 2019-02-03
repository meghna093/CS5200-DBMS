package edu.northeastern.cs5200.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.DriverManager;
import java.sql.Statement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Map;
import java.util.ArrayList;
import java.util.List;
import java.util.HashMap;

public class AddressDao extends EstConnDao {
	private final String crtAddress = "INSERT INTO address (`street1`, `street2`, `city`, `state`, `zip`, `primary`, `personId`) VALUES(?,?,?,?,?,?,?)";
	private final String updtAddress = "UPDATE address SET street1 = ?, street2 = ?, city = ?, state = ?, zip = ?, primary = ?, personId = ? WHERE address.id = ?;";
	private final String delAddress = "Delete FROM address WHERE address.id = ?;";
	private final String fndAddID =	"SELECT * FROM address WHERE id = ?";
	private final String fndAddPerson = "SELECT * FROM address JOIN person ON address.personId = person.id WHERE personId = ?";
	private static AddressDao instance = null;
	private static Map<Integer, Address> add = new HashMap<>();
	
	private AddressDao() {}
	public static AddressDao getInstance() {
		if (instance == null) {
			instance = new AddressDao();
		}
		return instance;
	}
	public Address findAddresseById(int addId) {
		Address res = null;
		Connection conn = null;
		PreparedStatement statement = null;
		ResultSet result = null;
		try {
			Class.forName(jdbc_drvr);
			conn = DriverManager.getConnection(url, user_name, psswd);
			statement = conn.prepareStatement(fndAddID);
			statement.setInt(1, addId);
			result = statement.executeQuery();
			if (result.next()) {
				String street1 = result.getString("street1");
				String street2 = result.getString("street2");
				String city = result.getString("city");
				String state = result.getString("state");
				String zip = result.getString("zip");
				Boolean primary = result.getBoolean("primary");
				res= new Address(street1, street2, city, state, zip, primary);	
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
	public List<Address> findAddressesForPerson(int pId) {
		List<Address> res = new ArrayList<>();
		Connection conn = null;
		PreparedStatement statement = null;
		ResultSet result = null;
		try {
			Class.forName(jdbc_drvr);
			conn = DriverManager.getConnection(url, user_name, psswd);
			statement = conn.prepareStatement(fndAddPerson);
			statement.setInt(1, pId);
			result = statement.executeQuery();
			while (result.next()) {
				String street1 = result.getString("street1");
				String street2 = result.getString("street2");
				String city = result.getString("city");
				String state = result.getString("state");
				String zip = result.getString("zip");
				Boolean primary = result.getBoolean("primary");
				Address address= new Address(street1, street2, city, state, zip, primary);
				res.add(address);
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
	public static Map<Integer, Address> getAddresses() {
		return add;
	}
	public int deleteAddress(int addId) {
		Connection conn = null;
		PreparedStatement statement = null;
		try {
			Class.forName(jdbc_drvr);
			conn = DriverManager.getConnection(url, user_name, psswd);
			statement = conn.prepareStatement(delAddress);
			statement.setInt(1,addId);
			statement.executeUpdate();
			add.remove(addId);
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
	public int updateAddress(int addId, Address address) {
		Connection conn = null;
		PreparedStatement statement = null;
		try {
			Class.forName(jdbc_drvr);
			conn = DriverManager.getConnection(url, user_name, psswd);
			statement = conn.prepareStatement(updtAddress);
			statement.setString(1, address.getStreet1());
			statement.setString(2, address.getStreet2());
			statement.setString(3, address.getCity());
			statement.setString(4, address.getState());
			statement.setString(5, address.getZip());
			statement.setBoolean(6, address.isPrimary());
			statement.setInt(7, addId);
			statement.executeUpdate();
			add.put(addId, address);
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
	public int createAddress(int pId, Address addr) {
		Connection conn = null;
		PreparedStatement statement = null;
		ResultSet result = null;
		try {
			Class.forName(jdbc_drvr);
			conn = DriverManager.getConnection(url, user_name, psswd);
			statement = conn.prepareStatement(crtAddress, Statement.RETURN_GENERATED_KEYS);
			statement.setString(1, addr.getStreet1());
			statement.setString(2, addr.getStreet2());
			statement.setString(3, addr.getCity());
			statement.setString(4, addr.getState());
			statement.setString(5, addr.getZip());
			statement.setBoolean(6, addr.isPrimary());
			statement.setInt(7, pId);
			statement.executeUpdate();
			result = statement.getGeneratedKeys();
			if(result.next()) {		   
				int id = result.getInt(1);
				add.put(id, addr);
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