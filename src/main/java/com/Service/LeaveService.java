package com.Service;

import java.util.ArrayList;
import java.util.List;

import com.Dao.LeaveDao;
import com.leave.LeaveCount;
import com.leave.LeaveTable;

public class LeaveService
{
	private LeaveDao leaveDao;
	
	public LeaveService()
	{
		this.leaveDao= LeaveDao();
	}

	private LeaveDao LeaveDao()
	{
		LeaveDao leaveDao = new LeaveDao();
		
		return leaveDao;
	}
	
	public String saveLeave(LeaveTable leave)
	{
		 String responce = null;
		 
		  try
		  {
		    responce = leaveDao.saveLeave(leave);
		  }
		  catch (Exception e)
		  {
			e.printStackTrace();
		  }
		  return responce;
	}
	
	public List<LeaveCount> getLeaves()
	{
		List<LeaveCount> leaves= new ArrayList<>();
		
		try
		{
		  leaves=leaveDao.getLeaves();
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
		
		return leaves;
	}
	
}
