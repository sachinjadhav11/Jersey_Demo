package com.Registration;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;


public class StudentDao 
{
   public static Connection getConnection() throws ClassNotFoundException
   {
	   Connection con=null;
	   
	   Class.forName("com.mysql.cj.jdbc.Driver");
	   
	     try
	     {
			con=DriverManager.getConnection(
					 "jdbc:mysql://localhost:3306/jersey_Demo", "root", "root");
			
		 }
	     catch (SQLException e)
	     {
			// TODO Auto-generated catch block
			e.printStackTrace();
		 }

	 	return con;    
   }
   
  
}
