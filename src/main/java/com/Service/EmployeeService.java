package com.Service;

import java.util.ArrayList;
import java.util.List;

import com.Dao.EmployeeDao;
import com.Registration.EmployeeRegister;
import com.Registration.LoginDao;

public class EmployeeService
{
	private EmployeeDao employeeDao;
	
    public EmployeeService()
	{
		this.employeeDao=EmployeeDao();
	}
	
	 private EmployeeDao EmployeeDao() 
	 {

	  EmployeeDao employeeDao = new EmployeeDao();
		
	  return employeeDao;
    }

	public EmployeeRegister getEmployeeById(int employeeId)
	{
		EmployeeRegister employeeRegister = new EmployeeRegister();
		
		try
		{
		  employeeRegister=employeeDao.getEmployeeById(employeeId);
		}
		catch (Exception e) 
		{
			e.printStackTrace();
		}
		return employeeRegister;
	}
	
	public void deleteEmployee(int employeeId)
	{
		try
		{
		 employeeDao.deleteEmployee(employeeId);
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
	}
	
	public String SignIn(LoginDao loginDao)
	{
		String responce=null;
		
		try
		{
			responce = employeeDao.SignIn(loginDao);
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
		
		return responce;
	}
	
	public List<EmployeeRegister> getAllData()
	{
		 List<EmployeeRegister> list= new ArrayList<>();
		 
		 try
		 {
		   list=employeeDao.getAllData();
		 }
		 catch (Exception e)
		 {
			e.printStackTrace();
		 }
		 return list;
	}
	
	public  String updateData(EmployeeRegister empReg)
	{
		String responce =null;
		
		try
		{
		  responce = employeeDao.updateData(empReg);
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
		return responce;
	}
	
	public String save(EmployeeRegister empReg)
	{
		String responce=null;
		
		try
		{
		  responce = employeeDao.save(empReg);
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
		return responce;
	}
	
	
}
