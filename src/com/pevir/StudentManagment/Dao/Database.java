package com.pevir.StudentManagment.Dao;

import java.sql.Connection;
import java.sql.DriverManager;

public class Database {
	
	public static Connection connectDb() {
		try {
			
			Class.forName("org.mariadb.jdbc.Driver");
			
			Connection connect = DriverManager.getConnection("jdbc:mariadb://localhost:3307/studentmanag", "root", "");
			
			return connect;
		
		}catch (Exception e) {e.printStackTrace(); }
		return null;
	}
} 