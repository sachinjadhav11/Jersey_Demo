package com.Registration;

import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import java.io.FileInputStream;
import java.io.InputStream;
import java.util.Properties;

public class EmailSender
{
	  public boolean sendEmail(String to, String from, String subject, String text)
	  {
	        boolean flag = false;
	        
	     try
	     {
	        Properties p = new Properties();
	        
	   	   InputStream is = new FileInputStream(""
		    	 + "D:\\Tekvision_Assignment\\jerseyDemo\\src\\main\\webapp\\WEB-INF\\db.properties");
		    	 
		     p.load(is);
	        
	        p.put("mail.smtp.auth", true);
	        p.put("mail.smtp.starttls.enable", true);
	        p.put("mail.smtp.port", "587");
	        p.put("mail.smtp.host", "smtp.gmail.com");
	        p.put("mail.smtp.ssl.protocols", "TLSv1.2");

	        String username = "sachinjadhav110399@gmail.com";
	        String password = "vebi zsle xgnc iqei";
	        
	        Session session = Session.getInstance(p, new Authenticator()
	        {
	            @Override
	            protected PasswordAuthentication getPasswordAuthentication()
	            {
	                return new PasswordAuthentication(username, password);
	            }
	        });

	            Message message = new MimeMessage(session);
	            message.setRecipient(Message.RecipientType.TO, new InternetAddress(to));
	            message.setFrom(new InternetAddress(from));
	            message.setSubject(subject);
	            message.setText(text);
	            Transport.send(message);
	            flag = true;
	        } 
	        
	        catch (Exception e) 
	        {
	            e.printStackTrace();
	        }
	        return flag;
	    }

 }



	 

	

