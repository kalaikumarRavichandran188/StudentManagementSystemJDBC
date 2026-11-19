package com.sms.repository;

import java.sql.SQLException;
import java.util.List;

import com.sms.model.Student;

public interface StudentRepository {

	boolean add(Student student) throws SQLException;

	List<Student> findAll() throws SQLException;

	Student findById(int id) throws SQLException;

	boolean update(int id, Student student) throws SQLException;

	boolean delete(int id) throws SQLException;
}
