package com.example.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ERSDbConnection 
{
	

	//input db endpoint, username and password here
	private static final String URL = "##";
	private static final String username = "##";
	private static final String password = "##";
	
	
	public Connection getDbConnection() throws SQLException
	{
		try {
			Class.forName("org.postgresql.Driver");
			return DriverManager.getConnection(URL, username, password);
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
	
}
