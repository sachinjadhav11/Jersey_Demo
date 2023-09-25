package com.leave;


public class LeaveTable 
{
    private int employeeId;
    
    private String empName;
    
    private String empMail;
    
    private String leaveBalance;
    
    private String date;
    
    private String description;

	public LeaveTable(int employeeId, String empName, String empMail, String leaveBalance, String date,
			String description)
	{
		super();
		this.employeeId = employeeId;
		this.empName = empName;
		this.empMail = empMail;
		this.leaveBalance = leaveBalance;
		this.date = date;
		this.description = description;
	}

	public LeaveTable()
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

	public String getEmpName() {
		return empName;
	}

	public void setEmpName(String empName) {
		this.empName = empName;
	}

	public String getEmpMail() {
		return empMail;
	}

	public void setEmpMail(String empMail) {
		this.empMail = empMail;
	}

	public String getLeaveBalance() {
		return leaveBalance;
	}

	public void setLeaveBalance(String leaveBalance) {
		this.leaveBalance = leaveBalance;
	}

	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	@Override
	public String toString()
	{
		return "LeaveTable [employeeId=" + employeeId + ", empName=" + empName + ", empMail=" + empMail
				+ ", leaveBalance=" + leaveBalance + ", date=" + date + ", description=" + description + "]";
	}
	
	
	
	

	

	
	
    
	
    
}
