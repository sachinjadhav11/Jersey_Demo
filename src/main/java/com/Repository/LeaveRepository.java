package com.Repository;

import java.util.List;

import com.leave.LeaveCount;
import com.leave.LeaveTable;

public interface LeaveRepository
{
	public String saveLeave(LeaveTable leave);
	  
	public List<LeaveCount> getLeaves();
}
