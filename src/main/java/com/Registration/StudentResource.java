package com.Registration;

import java.util.ArrayList;
import java.util.List;

import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

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
	public Student save(Student student)
	{
		studentRepository.save(student);
		System.out.println("save sucessfully");
		return student;
		
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
	public Student updateStudent(Student student)
	{
		studentRepository.updateStudent(student);
		System.out.println("Update sucessfully..");
		return student;
	}
	
	
}
