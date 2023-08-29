package com.Registration;

public class Student
{
	private int id;
	private String name;
	private String email;
	private String password;
	private String address;
	private String gender;
	private String phoneNo;
	private int dept_id;
	
	public Student(int id, String name, String email, String password, String address, String gender, String phoneNo,
			int dept_id)
	{
		super();
		this.id = id;
		this.name = name;
		this.email = email;
		this.password = password;
		this.address = address;
		this.gender = gender;
		this.phoneNo = phoneNo;
		this.dept_id = dept_id;
	}

	public Student()
	{
		super();
		// TODO Auto-generated constructor stub
	}

	public int getId()
	{
		return id;
	}

	public void setId(int id)
	{
		this.id = id;
	}

	public String getName()
	{
		return name;
	}

	public void setName(String name)
	{
		this.name = name;
	}

	public String getEmail()
	{
		return email;
	}

	public void setEmail(String email)
	{
		this.email = email;
	}

	public String getPassword()
	{
		return password;
	}

	public void setPassword(String password)
	{
		this.password = password;
	}

	public String getAddress()
	{
		return address;
	}

	public void setAddress(String address)
	{
		this.address = address;
	}

	public String getGender()
	{
		return gender;
	}

	public void setGender(String gender)
	{
		this.gender = gender;
	}

	public String getPhoneNo()
	{
		return phoneNo;
	}

	public void setPhoneNo(String phoneNo)
	{
		this.phoneNo = phoneNo;
	}

	public int getDept_id()
	{
		return dept_id;
	}

	public void setDept_id(int dept_id)
	{
		this.dept_id = dept_id;
	}

	@Override
	public String toString()
	{
		return "Student [id=" + id + ", name=" + name + ", email=" + email + ", password=" + password + ", address="
				+ address + ", gender=" + gender + ", phoneNo=" + phoneNo + ", dept_id=" + dept_id + "]";
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	//Tekvision@0617_1
	
	
	
	
	

	
	
	
	
	
	
	
}
