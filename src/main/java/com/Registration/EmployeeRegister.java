package com.Registration;

public class EmployeeRegister 
{
	private int employeeId;
	
	private String name;
	
	private String email;
	
	private String password;
	
	private String address;
	
	private String gender;
	
	private String phoneNo;
	
	private int deptId;
	
	private String deptName;
	
	 private int empId;

	public EmployeeRegister(int employeeId, String name, String email, String password, String address, String gender,
			String phoneNo, int deptId, String deptName, int empId) {
		super();
		this.employeeId = employeeId;
		this.name = name;
		this.email = email;
		this.password = password;
		this.address = address;
		this.gender = gender;
		this.phoneNo = phoneNo;
		this.deptId = deptId;
		this.deptName = deptName;
		this.empId = empId;
	}

	public EmployeeRegister()
	{
		super();
		// TODO Auto-generated constructor stub
	}

	public int getEmployeeId() {
		return employeeId;
	}

	public void setEmployeeId(int employeeId) {
		this.employeeId = employeeId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public String getPhoneNo() {
		return phoneNo;
	}

	public void setPhoneNo(String phoneNo) {
		this.phoneNo = phoneNo;
	}

	public int getDeptId() {
		return deptId;
	}

	public void setDeptId(int deptId) {
		this.deptId = deptId;
	}

	public String getDeptName() {
		return deptName;
	}

	public void setDeptName(String deptName) {
		this.deptName = deptName;
	}

	public int getEmpId() {
		return empId;
	}

	public void setEmpId(int empId) {
		this.empId = empId;
	}

	@Override
	public String toString()
	{
		return "EmployeeRegister [employeeId=" + employeeId + ", name=" + name + ", email=" + email + ", password="
				+ password + ", address=" + address + ", gender=" + gender + ", phoneNo=" + phoneNo + ", deptId="
				+ deptId + ", deptName=" + deptName + ", empId=" + empId + "]";
	}
	
	
	
	 
	//Tekvision@0617_1
	
	
	 
	 
	
}
