package com.Registration;

import java.sql.Connection;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
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
	public void save(Student student)
	{
		try
		{
			Connection con=StudentDao.getConnection();
			
			PreparedStatement ps=con.prepareStatement(
			"insert into jersey_demo.student(name, email, password, address, gender) values(?,?,?,?,?)");
			
			ps.setString(1, student.getName());
			ps.setString(2, student.getEmail());
			ps.setString(3, student.getPassword());
			ps.setString(4, student.getAddress());
			ps.setString(5, student.getGender());
			
			ps.executeUpdate();
			
			con.close();
			
		}
		catch (Exception e)
		{
			e.printStackTrace();
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
	public void updateStudent(Student student)
	{
		try
		{
			Connection con=StudentDao.getConnection();
			
			PreparedStatement ps=con.prepareStatement(
					"update jersey_demo.student set name=?, email=?, password=?,"
					+ " address=?,gender=? where id=?");
			
			  // ps.setInt(1,student.getId());
			   ps.setString(1, student.getName());
			   ps.setString(2, student.getEmail());
			   ps.setString(3, student.getPassword());
			   ps.setString(4, student.getAddress());
			   ps.setString(5, student.getGender());
			   ps.setInt(6, student.getId());
			   
			ps.executeUpdate();
			
			con.close();
			
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
		
	}
	
	}
    
	

