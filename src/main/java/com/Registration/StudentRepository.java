package com.Registration;

import java.util.List;

public interface StudentRepository 
{
  public List<Student> getAllStudent();
  
  public void save(Student student);
  
  public Student getStudentById(int id);
  
  public void deleteStudent(int id);
  
  public void updateStudent(Student student);
  
  
}
