package com.sms.repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.sms.model.Student;
import com.sms.util.DBConnection;

public class StudentRepositoryImpl implements StudentRepository{

	@Override
	public boolean add(Student student) throws SQLException {
		String sql = "INSERT INTO students " + "(student_name, email, department, phone_number)" + "VALUES(?,?,?,?)";
		try(Connection con = DBConnection.getConnection();
				PreparedStatement ps = con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)){
			ps.setString(1, student.getStudentName());
			ps.setString(2, student.getEmail());
			ps.setString(3, student.getDepartment());
			ps.setString(4, student.getPhoneNumber());
			int rows = ps.executeUpdate();
			
			if(rows == 1) {
				try(ResultSet keys = ps.getGeneratedKeys()) {
					if(keys.next()) {
						student.setStudentId(keys.getInt(1));
						return true;
					}
				}				
			}
			
		}
		return false;
	}

	@Override
	public List<Student> findAll() throws SQLException {
		List<Student> list = new ArrayList<>();
		String sql = "SELECT * FROM students";
		try(Connection con = DBConnection.getConnection();
				PreparedStatement ps = con.prepareStatement(sql);
				ResultSet rs = ps.executeQuery()){
			while(rs.next()) {
				list.add(mapRow(rs));
			}
		}
		return list;
	}

	private Student mapRow(ResultSet rs) throws SQLException {
		
		return new Student(rs.getInt("student_id"),rs.getString("student_name"),rs.getString("email"), rs.getString("department"), rs.getString("phone_number"));
	}

	@Override
	public Student findById(int id) throws SQLException {
		String sql = "SELECT * FROM students WHERE student_id=?";
		try(Connection con = DBConnection.getConnection();
				PreparedStatement ps = con.prepareStatement(sql)){
			ps.setInt(1, id);
			try(ResultSet rs = ps.executeQuery()){
				return rs.next() ? mapRow(rs) : null;
			}
		}
		
	}

	@Override
	public boolean update(int id, Student student) throws SQLException {
		String sql = "UPDATE students SET student_name=?, email=?, department=?, phone_number=? WHERE student_id=?";
		try(Connection con = DBConnection.getConnection();
				PreparedStatement ps = con.prepareStatement(sql)){
			ps.setString(1, student.getStudentName());
			ps.setString(2, student.getEmail());
			ps.setString(3, student.getDepartment());
			ps.setString(4, student.getPhoneNumber());
			ps.setInt(5, id);
			return ps.executeUpdate() == 1;
		}
		
	}

	@Override
	public boolean delete(int id) throws SQLException {
		String sql = "DELETE FROM students WHERE student_id=?";
		try(Connection con = DBConnection.getConnection();
				PreparedStatement ps = con.prepareStatement(sql)){
			ps.setInt(1, id);
		  	return ps.executeUpdate() == 1;
		}
		
	}
	
	
	}
