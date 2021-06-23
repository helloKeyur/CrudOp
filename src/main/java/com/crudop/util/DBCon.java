package com.crudop.util;

import java.sql.Connection;
import java.sql.DriverManager;

public class DBCon {
	private static String url = "jdbc:oracle:thin:@localhost:1521:XE";
	private static String username = "system";
	private static String password = "123456";
	
	public static Connection getConnection() {
		Connection con = null;
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			con = DriverManager.getConnection(url,username,password);
		}catch(Exception e) {
			System.out.print(e.getMessage());
		}
		return con;
	}
}
