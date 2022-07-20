package com.mini_project;

import java.sql.*;
public class AConnection {
	static Connection connect=null; 
	public static Connection getConnection()
	{
		try {
			
			
			Class.forName("oracle.jdbc.driver.OracleDriver");
			
			String url ="jdbc:oracle:thin:@localhost:1521:xe";
			String username="banking";
			String password="abhay@123";
			connect = DriverManager.getConnection(url, username, password);
			
			if(connect!=null) {
				System.out.println("");
			}
			else {
				System.out.println("Can't connect");
			}
		}
		catch (Exception e) {
			System.out.println("Connection Failed!");
		}
    return connect;
		
	}
}