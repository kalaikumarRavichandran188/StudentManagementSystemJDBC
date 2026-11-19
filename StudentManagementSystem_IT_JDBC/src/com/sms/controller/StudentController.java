package com.sms.controller;

import java.sql.SQLException;
import java.util.List;

import com.sms.model.Student;

import com.sms.service.StudentService;

public class StudentController {
	private final StudentService service;
	public StudentController(StudentService service) {
		this.service = service;
	}
	
	public boolean add(Student student) throws SQLException {
		return service.addStudent(student);
	}
	public List<Student> getAll()throws SQLException{
		return service.getAllStudents();
	}
	public Student getById(int id)throws SQLException {
		return service.getStudentById(id);
	}
	public boolean update(int id,Student student) throws SQLException{
		return service.updateStudent(id,student);
	}
	public boolean delete(int id) throws SQLException{
		return service.deleteStudent(id);
	}
}
