package com.sms.service;



import java.sql.SQLException;
import java.util.List;



import com.sms.model.Student;



public interface StudentService {

	// CRUD related

	/**

	 * Method: - Add std - Get all std - Get std by id - View all std - Update std

	 * by id - Delete std by id

	 */



	boolean addStudent(Student student) throws SQLException;



	List<Student> getAllStudents() throws SQLException;



	Student getStudentById(int id) throws SQLException;



	boolean updateStudent(int id, Student student) throws SQLException;



	boolean deleteStudent(int id) throws SQLException;

}

