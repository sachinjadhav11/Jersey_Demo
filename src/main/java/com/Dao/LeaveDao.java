package com.Dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import com.Registration.EmailSender;
import com.Repository.LeaveRepository;
import com.leave.LeaveCount;
import com.leave.LeaveTable;

public class LeaveDao implements LeaveRepository
{
	
	public LeaveDao()
	{
		
	}
   
	private final String getLeaves = "SELECT DISTINCT l.*, subquery.leave_count, (l.leaveBalance - subquery.leave_count) AS remainingBalance "
			+ "FROM jersey_demo.leave2 AS l "
			+ "JOIN (SELECT employeeId, COUNT(id) AS leave_count FROM jersey_demo.leave2 "
			+ "GROUP BY employeeId ) AS subquery ON l.employeeId = subquery.employeeId;";
	
	private final String leave_query = "insert into jersey_demo.leave2(employeeId, empName, email, leaveBalance, "
			+ " date, description) values(?, ?, ?, ?, ?, ?)";
	
	@Override
	public String saveLeave(LeaveTable leave)
	{
		Connection con=null;
		PreparedStatement ps= null;
		
		String balance="10";
		
		try
		{
			EmailSender mailSender = new EmailSender();
			String to=leave.getEmpMail();
			String from = "sachinjadhav113099@gmail.com";
			String subject = "Regarding to the leave";
			String text = "Leave Granted Sucessfully";
			
			con=ConnectionLoader.getConnection();
			
			ps=con.prepareStatement(leave_query);
			
			ps.setInt(1, leave.getEmployeeId());
			ps.setString(2, leave.getEmpName());
			ps.setString(3, leave.getEmpMail());
			ps.setString(4, balance);
			ps.setString(5, leave.getDate());
			ps.setString(6, leave.getDescription());
			
			System.out.println(leave);
			
			ps.executeUpdate();
			
			mailSender.sendEmail(to, from, subject, text);
			
			con.close();
			
			return "success";
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
		return "fail";
	}

	@Override
	public List<LeaveCount> getLeaves()
	{
		List<LeaveCount> leaves= new ArrayList<>();
		
		Connection con=null;
		
		try
		{
			con=ConnectionLoader.getConnection();
			
			PreparedStatement ps=con.prepareStatement(getLeaves);
			
			ResultSet rs=ps.executeQuery();
			
			while(rs.next())
			{
				LeaveCount leavetb= new LeaveCount();
				
				leavetb.setEmployeeId(rs.getInt(2));
				leavetb.setEmpName(rs.getString(3));
				leavetb.setEmpMail(rs.getString(4));
				leavetb.setLeaveBalance(rs.getString(5));
				leavetb.setDate(rs.getString(6));
				leavetb.setDescription(rs.getString(7));
				leavetb.setLeave_count(rs.getInt(8));
				leavetb.setRemainingLeave(rs.getInt(9));
				leaves.add(leavetb);
			}	
			con.close();
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
		return leaves;
	}
}
