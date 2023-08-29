package com.Registration;

import java.io.FileInputStream;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.util.Properties;


public class StudentDao 
{
   public static Connection getConnection() 
   {
	   Connection con=null;
	   
	      try
	      {
	    	 Properties p= new Properties();
	    	 
	    	 InputStream is = new FileInputStream(""
	    	 + "D:\\Tekvision_Assignment\\jerseyDemo\\src\\main\\webapp\\WEB-INF\\db.properties");
	    	 
	    	 p.load(is);
	    	 
	    	 String url=p.getProperty("url");
	    	 String username1=p.getProperty("username");
	    	 String password1=p.getProperty("password");
	    	 
	    	 Class.forName("com.mysql.cj.jdbc.Driver"); 
	    	
			con=DriverManager.getConnection(url, username1, password1);
	      } 
	      catch (Exception e)
	      {
			 e.printStackTrace();
		  }
	 	return con;    
   }
   
}

