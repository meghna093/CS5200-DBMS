package edu.northeastern.cs5200.model;
import java.io.IOException;
import java.sql.DriverManager;
import java.sql.Connection;
import java.sql.SQLException;

public class EstConnDao {
	public static final String jdbc_drvr = "com.mysql.jdbc.Driver";
	public static final String url = "jdbc:mysql://cs5200-spring2018-venkatesha.c94yi0ekyjtk.us-east-2.rds.amazonaws.com/cs5200_spring2018_venkatesha?autoReconnect=true&useSSL=false";
	public static final String user_name = "meghnavenkatesha";
	public static final String psswd = "meghna123";

	public static void estConn(String path) throws IOException, SQLException, ClassNotFoundException {
		Class.forName(EstConnDao.jdbc_drvr);
		Connection conn = DriverManager.getConnection(EstConnDao.url, EstConnDao.user_name, EstConnDao.psswd);
		conn.close();
	}

}

