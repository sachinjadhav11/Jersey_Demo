package com.Registration;


import java.sql.Connection;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Base64;
import java.util.List;


public class StudentRepo implements StudentRepository
{
	public StudentRepo()
	{
		
	}

	@Override
	public List<Student> getAllStudent()
	{
	   List<Student> student= new ArrayList<Student>();
			    
			   try 
			   {
				    Connection con=StudentDao.getConnection();
				   
				    PreparedStatement ps=con.prepareStatement("SELECT * FROM jersey_demo.student");	
				    ResultSet rs=ps.executeQuery();
	    
				    while(rs.next())
				    {
				    	Student std= new Student();
				    	std.setId(rs.getInt(1));
				    	std.setName(rs.getString(2));
				    	std.setEmail(rs.getString(3));
				    	std.setPassword(rs.getString(4));
				    	std.setAddress(rs.getString(5));
				    	std.setGender(rs.getString(6));
				    	std.setPhoneNo(rs.getString(7));
				    	std.setDept_id(rs.getInt(8));
				    	student.add(std);
				    }
				    con.close();   
			   }
			   catch (Exception e)
			   {
				 e.printStackTrace();
			   }
			   return student;
	 }
	
	@Override
	public String save(Student student)
	{
	    try
	    {
	        EmailSender mailSender = new EmailSender();

	        String to = student.getEmail();
	        String from = "sachinjadhav110399@gmail.com";
	        String subject = "Registration Successfully";
	        String text = "Congratulations! Your Registration is Successful!";

	        List<Student> allStudents = getAllStudent();
	        for (Student s : allStudents)
	        {
	            if (s.getEmail().equals(student.getEmail()))
	            {
	                return "Email id already exists";
	            }
	        }

	        byte[] encryptedPassword = Base64.getEncoder().encode(student.getPassword().getBytes());
	        String pass1 = new String(encryptedPassword);

	        Connection con = null;
	        PreparedStatement ps = null;
	        try
	        {
	            con = StudentDao.getConnection();
	            ps = con.prepareStatement("insert into jersey_demo.student(name, email, password, address, gender, phoneNo, dept_id) values(?,?,?,?,?,?,?)");

	            ps.setString(1, student.getName());
	            ps.setString(2, student.getEmail());
	            ps.setString(3, pass1);
	            ps.setString(4, student.getAddress());
	            ps.setString(5, student.getGender());
	            ps.setString(6, student.getPhoneNo());
	            ps.setInt(7, student.getDept_id());

	            ps.executeUpdate();
	            
	            mailSender.sendEmail(to, from, subject, text);
	            
	            return "success";
	        }
	        
	        catch (SQLException e)
	        {
	            e.printStackTrace();
	            return "Database error";
	            
	        }
	        
	        finally
	        {
	            if (ps != null)
	            {
	                ps.close();
	            }
	            if (con != null)
	            {
	                con.close();
	            }
	        }
	    }
	    catch (Exception e)
	    {
	        e.printStackTrace();
	        return "Unknown error";
	    }
	}

	@Override
	public Student getStudentById(int id)
	{
		Student student= new Student();
		
		try{
			Connection con=StudentDao.getConnection();
			
			PreparedStatement ps=con.prepareStatement(
					"SELECT * FROM jersey_demo.student where id="+id);
			
			ResultSet rs=ps.executeQuery();
			
			if(rs.next())
			{
				student.setId(rs.getInt(1));
				student.setName(rs.getString(2));
				student.setEmail(rs.getString(3));
				student.setPassword(rs.getString(4));
				student.setAddress(rs.getString(5));
				student.setGender(rs.getString(6));
			}
			
			con.close();
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
		
		return student;
	}

	@Override
	public void deleteStudent(int id)
	{
		try
		{
			Connection con=StudentDao.getConnection();
			
			PreparedStatement ps=con.prepareStatement(
					"delete from jersey_demo.student where id="+id);
			
			ps.executeUpdate();
			
			con.close();
			
		}catch (Exception e)
		{
			e.printStackTrace();
		}	
	}
	
	@Override
	public String updateStudent(Student student)
	{
	    try
	    {
	       EmailSender mailSender = new EmailSender();
		   String to = student.getEmail();
		   String from = "sachinjadhav110399@gmail.com";
		   String subject = "Update Successfully";
		   String text = "Congratulations! Your Information Update Successfully!";
	    
	        Connection con = StudentDao.getConnection();
	        
	        byte [] encrypt=Base64.getEncoder().encode(student.getPassword().getBytes());
	        
	        String pass1= new String(encrypt);
	        
	        System.out.println("encrypted pass1 "+pass1);
	       
	        PreparedStatement ps = con.prepareStatement(
	                "UPDATE jersey_demo.student SET name=?, email=?, password=?, address=?, gender=?, phoneNo=?, dept_id=? WHERE id=?");
	        
	        ps.setString(1, student.getName());
	        ps.setString(2, student.getEmail());
	        ps.setString(3, pass1);
	        ps.setString(4, student.getAddress());
	        ps.setString(5, student.getGender());
	        ps.setString(6, student.getPhoneNo());
	        ps.setInt(7, student.getDept_id());
	        ps.setInt(8, student.getId());
	        
	        int cnt = ps.executeUpdate();
	        
	        mailSender.sendEmail(to, from, subject, text);
	        
	        System.out.println("PS "+ps);
	        
	        System.out.println("Data updated count: " + cnt);
	        
	        con.close();
	        
	        if (cnt > 0)
	        {
	            return "Success";
	        }
	        else
	        {
	        	System.out.println("Not Updated data");
	            return "Not data updated";
	        }
	    } 
	    catch (Exception e)
	    {
	        e.printStackTrace();
	        System.out.println("Fail..");
	        return "Fail: " + e.getMessage();
	    }
	}

	@Override
	public String SignIn(LoginDao loginDao)
	{
		Student student= new Student();
		try
		{
			Connection con=StudentDao.getConnection();
			
			PreparedStatement ps=con.prepareStatement("select * from jersey_demo.student"
					+ " where email=? and password=?");
			
			ps.setString(1, loginDao.getEmail());
			ps.setString(2, loginDao.getPassword());
			
			ResultSet rs=ps.executeQuery();
			
			if(rs.next())
			{
				student.setEmail(rs.getString(1));
				student.setPassword(rs.getString(2));
			}
			
			System.out.println("LoginDao "+loginDao.getPassword());
			System.out.println("student "+student.getPassword());
			
			if((loginDao.getEmail().isEmpty()) && (loginDao.getPassword().isEmpty()))
			{
				return "fail";
			}

			for (Student s : getAllStudent()) {
				if (s.getEmail().equals(loginDao.getEmail())) {
					byte[] decrypt1 = Base64.getDecoder().decode(s.getPassword().getBytes());

					String pass1 = new String(decrypt1);

					System.out.println("Decrypted Password "+pass1);

				  System.out.println(s);
				  
					if ((s.getEmail().equals(loginDao.getEmail())) && (pass1.equals(loginDao.getPassword())))
					{
						System.out.println("Pass1 "+pass1);
						
						System.out.println("loginDao "+loginDao.getPassword());
						
						System.out.println("login success");
						return "Success";
					}
				}

			}
			 System.out.println("fail");
			  return "fail";
		
		}
		catch (Exception e)
		{
			e.printStackTrace();
        }
		
		return "fail";
		
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
//	  @Override public Student UserSign(String email, String password)
//	  {
//
//			Student student = new Student();
//
//			try {
//				Connection con = StudentDao.getConnection();
//				PreparedStatement ps = con
//						.prepareStatement("" + "select * from jersey_demo.student where email=? and password=?");
//
//				ps.setString(1, email);
//				ps.setString(2, password);
// 
//				ResultSet rs = ps.executeQuery();
//
//				while (rs.next()) {
//					student.setEmail(rs.getString(1));
//					student.setPassword(rs.getString(2));
//				}
//
//				con.close();
//
//			} catch (Exception e) {
//				e.printStackTrace();
//			}
//			return student;
//		}

	
}
    
	

