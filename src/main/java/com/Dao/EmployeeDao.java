package com.Dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Base64;
import java.util.List;

import com.Registration.EmailSender;
import com.Registration.EmployeeRegister;
import com.Registration.LoginDao;
import com.Repository.EmployeeRepository;

public class EmployeeDao implements EmployeeRepository
{
	public EmployeeDao()
	{
		
	}
	
private final String Sign_In_Query = "select * from jersey_demo.employee where email=? and password=?";

private final String Get_Query = "select e.employeeId, e.name, e.email, e.password, "
		+ "e.address, e.gender, e.phoneNo, d.id, d.deptName from jersey_demo.employee "
 + "e join jersey_demo.department d on e.employeeId=d.empId"; 

private final String Update_Query = "UPDATE jersey_demo.employee e "
		+ "JOIN jersey_demo.department d ON e.employeeId = d.empId "
		+ "SET e.name=?, e.email=?, e.password=?, e.address=?, e.gender=?, e.phoneNo=?, d.deptName=? "
		+ "where e.employeeId=?";

	@Override
	public EmployeeRegister getEmployeeById(int employeeId)
	{
		 EmployeeRegister empReg= new EmployeeRegister();
		 
		 Connection con=null;
		
		try
		{
		     con=ConnectionLoader.getConnection();
			
			PreparedStatement ps=con.prepareStatement(
					"SELECT * FROM jersey_demo.employee where employeeId="+employeeId);
		     
			ResultSet rs=ps.executeQuery();
			
			if(rs.next())
			{
				empReg.setEmployeeId(rs.getInt(1));
				empReg.setName(rs.getString(2));
				empReg.setEmail(rs.getString(3));
				empReg.setPassword(rs.getString(4));
				empReg.setAddress(rs.getString(5));
				empReg.setGender(rs.getString(6));
				empReg.setPhoneNo(rs.getString(7));
			}
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
		finally
		{
			try
			{
				if(con!=null)
				{
				 con.close();
				}
			} 
			catch (SQLException e)
			{
				e.printStackTrace();
			}
		}
		return empReg;
	}

	@Override
	public void deleteEmployee(int employeeId)
	{
		Connection con=null;
		
		try
		{
			con=ConnectionLoader.getConnection();
			
			PreparedStatement ps=con.prepareStatement(
					"delete from jersey_demo.employee where employeeId="+employeeId);
			
			ps.executeUpdate();
			
			con.close();
			
		}catch (Exception e)
		{
			e.printStackTrace();
		}	
	}
	
	@Override
	public String SignIn(LoginDao loginDao)
	{
		EmployeeRegister employee = new EmployeeRegister();
		
		Connection con=null;
		try
		{
			con=ConnectionLoader.getConnection();
		    
			PreparedStatement ps=con.prepareStatement(Sign_In_Query);
					
			ps.setString(1, loginDao.getEmail());
			ps.setString(2, loginDao.getPassword());
			
			ResultSet rs=ps.executeQuery();
			
			if(rs.next())
			{
				employee.setEmail(rs.getString(1));
				employee.setPassword(rs.getString(2));
			}
		
			if((loginDao.getEmail().isEmpty()) && (loginDao.getPassword().isEmpty()))
			{
				return "fail";
			}
			for (EmployeeRegister s : getAllData())
			{
			 if (s.getEmail().equals(loginDao.getEmail()))
			 {
				byte[] decrypt1 = Base64.getDecoder().decode(s.getPassword().getBytes());

				String pass1 = new String(decrypt1);

				System.out.println("Decrypted Password "+pass1);
					
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
		
		finally
		{
			if(con !=null)
			{
				try
				{
				  con.close();  
				}
				catch (SQLException e2)
				{
					e2.printStackTrace();
				}
			}
		}
		return "fail";
	}

	@Override
	public String save(EmployeeRegister empReg)
	{
		int empId = 0;
		Connection con = null;
		PreparedStatement ps = null;
	
		try
		{
			EmailSender mailSender = new EmailSender();

			String to = empReg.getEmail();
			String from = "sachinjadhav110399@gmail.com";
			String subject = "Registration Successfully";
			String text = "Congratulations! Your Registration is Successful!";

			boolean result = checkEmail(empReg.getEmail());
			
			if(!result)
			{
				return "Email id already Exists";
			}
			
			byte[] encryptedPassword = Base64.getEncoder().encode(empReg.getPassword().getBytes());
			String pass1 = new String(encryptedPassword);

			try
			{
				con = ConnectionLoader.getConnection();

				ps = con.prepareStatement(
						"insert into jersey_demo.employee(name, email, password, address, gender, phoneNo)"
								+ " values(?,?,?,?,?,?)",
						Statement.RETURN_GENERATED_KEYS);
				
				ps.setString(1, empReg.getName());
				ps.setString(2, empReg.getEmail());
				ps.setString(3, pass1);
				ps.setString(4, empReg.getAddress());
				ps.setString(5, empReg.getGender());
				ps.setString(6, empReg.getPhoneNo());

				ps.executeUpdate();

				mailSender.sendEmail(to, from, subject, text);

				ResultSet rs = ps.getGeneratedKeys();

				if (rs.next()) {
					empId = rs.getInt(1);
				} else {
					System.out.println("Employee Registration Failed");
				}
				try
				{
					int departmentResult = EmployeeDao.saveDepartment(empReg.getDeptName(), empId);

					if (departmentResult > 0)
					{
						return "success";
					} else 
					{
						return "Department save failed";
					}
				}
				catch (Exception e)
				{
					e.printStackTrace();
				}
				return "success";
			}
			catch (SQLException e)
			{
				e.printStackTrace();
				return "Database error";
			}

		} 
		catch (Exception e)
		{
			e.printStackTrace();
			return "Something went wrong";
		}

		finally
		{
			try 
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
			catch (Exception e)
			{
				e.printStackTrace();
			}
		}
	}
	
	private boolean checkEmail(String email)
	{
		boolean result=true;
		
		List<EmployeeRegister> allEmployee = getAllData();
		
		for(EmployeeRegister employee : allEmployee)
		{
			if(employee.getEmail().equals(email))
			{
				result=false;
				break;
			}
		}
		return result;
	}

	public static int saveDepartment(String deptName, int empId) throws SQLException
	{
		Connection con = null;
		PreparedStatement ps = null;

		try
		{
			con = ConnectionLoader.getConnection();
			ps = con.prepareStatement("INSERT INTO jersey_demo.department(deptName, empId) VALUES (?, ?)");
			ps.setString(1, deptName);
			ps.setInt(2, empId);

			return ps.executeUpdate();
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
	
	@Override
	public List<EmployeeRegister> getAllData()
	{
	  List<EmployeeRegister> list= new ArrayList<>();
	  
	  Connection con=null;
	  
	  try
	  {
		  con=ConnectionLoader.getConnection();
	  
	      PreparedStatement ps=con.prepareStatement(Get_Query);
	      
	      ResultSet rs=ps.executeQuery();
	      
	      while(rs.next())
	      {
	    	 EmployeeRegister employee= new EmployeeRegister() ;
	    	 employee.setEmployeeId(rs.getInt(1));
	    	 employee.setName(rs.getString(2));
	    	 employee.setEmail(rs.getString(3));
	    	 employee.setPassword(rs.getString(4));
	    	 employee.setAddress(rs.getString(5));
	    	 employee.setGender(rs.getString(6));
	    	 employee.setPhoneNo(rs.getString(7));
	    	 employee.setDeptId(rs.getInt(8));
	    	 employee.setDeptName(rs.getString(9));
			 list.add(employee);
	      }
     }
	  catch (Exception e)
	  {
	     e.printStackTrace();
	  }
	  finally
	  {
		  if(con!=null)
		  {
			  try
			  {
				con.close();
			  }
			  catch (SQLException e2)
			  {
				e2.printStackTrace();
			  }
		  }
	  }
	  return list;
  }
	@Override
	public  String updateData(EmployeeRegister empReg)
	{
		Connection con = null;
		PreparedStatement psEmployee = null;

		try
		{
			EmailSender mailSender = new EmailSender();
			String to = empReg.getEmail();
			String from = "sachinjadhav110399@gmail.com";
			String subject = "Update Successfully";
			String text = "Congratulations! Your Information Update Successfully!";

			con=ConnectionLoader.getConnection();

			byte[] encrypt = Base64.getEncoder().encode(empReg.getPassword().getBytes());
			String pass1 = new String(encrypt);

			psEmployee = con.prepareStatement(Update_Query); 

			psEmployee.setString(1, empReg.getName());
			psEmployee.setString(2, empReg.getEmail());
			psEmployee.setString(3, pass1);
			psEmployee.setString(4, empReg.getAddress());
			psEmployee.setString(5, empReg.getGender());
			psEmployee.setString(6, empReg.getPhoneNo());
			psEmployee.setString(7, empReg.getDeptName());
			psEmployee.setInt(8, empReg.getEmployeeId());

			int cnt = psEmployee.executeUpdate();

			mailSender.sendEmail(to, from, subject, text);

			System.out.println("Data updated count: " + cnt);

			if (cnt > 0)
			{
				System.out.println("Data Updated");
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
		finally
		{
			if(con!=null)
			{
				try
				{
					con.close();
				}
				catch (SQLException e2)
				{
					e2.printStackTrace();
				}
			}
		}
	}
		
}
