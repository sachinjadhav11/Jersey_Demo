package com.Registration;


import java.net.http.HttpResponse;
import java.util.List;

import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path("/student")
public class StudentResource
{
	private StudentRepository studentRepository;
	
	public StudentResource()
	{
		studentRepository= new StudentRepo();
	}
	
	@GET
	@Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
	public List<Student> getAllStudent()
	{
		 return studentRepository.getAllStudent();
	}
	
	@POST
	@Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
	public Response save(Student student)
	{
		String s1=studentRepository.save(student);
		if(s1=="success")
		{
		 return Response.ok().build();
		}
		else
		return Response.notModified().build();
		
	}
	
	@GET
	@Path("/{id}")
	@Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
	public Student getStudentById(@PathParam("id") int id)
	{
		Student student= new Student();
		
		student= studentRepository.getStudentById(id);
		
		System.out.println(student);
		
		return student;
	}
	
	@DELETE
	@Path("/{id}")
	@Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
	public Student deleteStudent(@PathParam("id") int id)
	{
		studentRepository.deleteStudent(id);
		System.out.println("delete Sucessfully..");
		return studentRepository.getStudentById(id);
		
	}
	
	@PUT
	@Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
	public Response updateStudent(Student student)
	{
	    String res = studentRepository.updateStudent(student);
	    
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
		String loginMessage = studentRepository.SignIn(loginDao);
		
		System.out.println(loginMessage);
		
		if(loginMessage=="Success") {
			return Response.ok().build();
		}
		return Response.notModified().build();
	}
	
	
	
	
	/*
	 * @GET
	 * 
	 * @Path("/{email}/{password}")
	 * 
	 * @Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_ATOM_XML})
	 * public Student UserSign(@PathParam("email") String
	 * email, @PathParam("password") String password) { Student student= new
	 * Student();
	 * 
	 * for(Student s: getAllStudent()) { if(s.getEmail().equals(email) &&
	 * s.getPassword().equals(password)) { student=s; break; } }
	 * 
	 * System.out.println(email); System.out.println(password);
	 * System.out.println(student.getEmail());
	 * System.out.println(student.getPassword());
	 * 
	 * return student; }
	 */
	
	
	
	
}
	
	

