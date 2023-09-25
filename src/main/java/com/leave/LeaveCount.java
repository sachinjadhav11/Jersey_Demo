package com.leave;

public class LeaveCount
{
    private int employeeId;
    
    private String empName;
    
    private String empMail;
    
    private String leaveBalance;
    
    private String date;
    
    private String description;
    
    private int leave_count;
    
    private int remainingLeave;

	public LeaveCount(int employeeId, String empName, String empMail, String leaveBalance, String date, String description,
			int leave_count, int remainingLeave)
	{
		super();
		this.employeeId = employeeId;
		this.empName = empName;
		this.empMail = empMail;
		this.leaveBalance = leaveBalance;
		this.date = date;
		this.description = description;
		this.leave_count = leave_count;
		this.remainingLeave = remainingLeave;
	}

	public LeaveCount()
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

	public int getLeave_count() {
		return leave_count;
	}

	public void setLeave_count(int leave_count) {
		this.leave_count = leave_count;
	}
	
	public int getRemainingLeave() {
		return remainingLeave;
	}

	public void setRemainingLeave(int remainingLeave) {
		this.remainingLeave = remainingLeave;
	}
	
	public String getLeaveBalance() {
		return leaveBalance;
	}

	public void setLeaveBalance(String leaveBalance) {
		this.leaveBalance = leaveBalance;
	}

	@Override
	public String toString()
	{
		return "LeaveCount [employeeId=" + employeeId + ", empName=" + empName + ", empMail=" + empMail
				+ ", leaveBalance=" + leaveBalance + ", date=" + date + ", description=" + description
				+ ", leave_count=" + leave_count + ", remainingLeave=" + remainingLeave + "]";
	}

	

	
	
	
	
	
    
    
}
