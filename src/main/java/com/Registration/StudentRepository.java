package com.Registration;

import java.util.List;

public interface StudentRepository {
	public List<Student> getAllStudent();

	public void save(Student student);

	public Student getStudentById(int id);

	public void deleteStudent(int id);

	public void updateStudent(Student student);

}

//http://localhost:9191/jerseyDemo/webapi/student

/*
 * function deleteStudent(id) {
 * fetch("http://localhost:9191/jerseyDemo/webapi/student/?id="+id )
 * .then((res)=>res.json())
 * 
 * fetch('//localhost:9191/jerseyDemo/webapi/student/' + id, { method: 'DELETE',
 * }) .then(res => res.json()) // or res.json() .then(res => console.log(res))
 * 
 * 
 * console.log(id); }
 */
