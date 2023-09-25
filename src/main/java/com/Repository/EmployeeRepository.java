package com.Repository;

import java.util.List;

import com.Registration.EmployeeRegister;
import com.Registration.LoginDao;

public interface EmployeeRepository
{
    public List<EmployeeRegister> getAllData();
	
	public String save(EmployeeRegister empReg);

	public EmployeeRegister getEmployeeById(int employeeId);

	public void deleteEmployee(int employeeId);

	public String updateData(EmployeeRegister empReg);

	public String SignIn(LoginDao loginDao);
}
