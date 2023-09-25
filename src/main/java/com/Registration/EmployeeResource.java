package com.Registration;



import java.util.List;

import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import com.Dao.EmployeeDao;
import com.Repository.EmployeeRepository;
import com.Service.EmployeeService;
import com.Service.LeaveService;
import com.leave.LeaveCount;
import com.leave.LeaveTable;

@Path("/student")
public class EmployeeResource
{

	private EmployeeService employeeService;
	private LeaveService leaveService;
	
	public EmployeeResource()
	{
		this.employeeService=EmployeeService();
		this.leaveService = LeaveService();
	}
	
  private LeaveService LeaveService()
  {
	  LeaveService leaveService = new LeaveService();
	  
	  return leaveService;
  }

  private EmployeeService EmployeeService()
  {
		EmployeeService employeeSerive= new EmployeeService();
		return employeeSerive;
  }

	@GET
	@Produces({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
	public List<EmployeeRegister> getAllStudent()
	{
		return employeeService.getAllData();
	}
	
	@GET
	@Path("/{employeeId}")
	@Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
	public EmployeeRegister getEmployeeById(@PathParam("employeeId") int employeeId)
	{
		EmployeeRegister employee= new EmployeeRegister();
		
		employee= employeeService.getEmployeeById(employeeId);
		
		System.out.println(employee);
		
		return employee;
	}
	
	@DELETE
	@Path("/{employeeId}")
	@Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
	public EmployeeRegister deleteEmployee(@PathParam("employeeId") int employeeId)
	{
		employeeService.deleteEmployee(employeeId);
		System.out.println("delete Sucessfully..");
		return employeeService.getEmployeeById(employeeId);
		
	}
	
	@PUT
	@Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
	public Response updateData(EmployeeRegister empReg)
	{
		//System.out.println(studentReg);
	    String res = employeeService.updateData(empReg);
	    
	    
	    if ("Success".equals(res))
	    {
	    	System.out.println("Success data updated");
	        return Response.ok().build();
	    }
	    
	    return Response.notModified().build();
	}

	@POST
	@Path("/Login")
	@Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_JSON})
	public Response SignIn(LoginDao loginDao)
	{
		String loginMessage = employeeService.SignIn(loginDao);
		
		System.out.println(loginMessage);
		
		if(loginMessage=="Success") {
			return Response.ok().build();
		}
		return Response.notModified().build();
	}
	
	@POST
	@Produces({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
	public Response save(EmployeeRegister studentReg)
	{
		String s1 = employeeService.save(studentReg);
		if (s1 == "success")
		{
			return Response.ok().build();
		}
		else
			return Response.notModified().build();
	}

	  @POST
	  @Path("/leave")
	  @Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
	  public Response save(LeaveTable leave)
	  {
		  String res=leaveService.saveLeave(leave);
		  
		  if(res=="success")
		  {
			return  Response.ok().build();
		  }
		  else
			  return Response.notModified().build();
	  }
	  
	  @GET
	  @Path("/leave")
	  @Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
	  public List<LeaveCount> getLeaves()
	  {
		 return leaveService.getLeaves();
	  }
	 
	
}
	
	

