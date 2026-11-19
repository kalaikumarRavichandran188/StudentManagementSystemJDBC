package com.sms.view;



import java.sql.SQLException;
import java.util.Scanner;



import com.sms.controller.StudentController;

import com.sms.model.Student;



public class StudentView {

	private final StudentController controller;

	

	private final Scanner sc = new Scanner(System.in);

	

	public StudentView(StudentController controller) {

		this.controller = controller;

	}

	

	private Student readStudent(int id) {

		System.out.println("Student Name: ");

		String name = sc.nextLine();

		

		System.out.println("Student Email: ");

		String email = sc.nextLine();

		

		System.out.println("Student Department: ");

		String department = sc.nextLine();

		

		System.out.println("Phone (10-50 digits): ");

		String phone = sc.nextLine();

		return new Student(id, name, email, department, phone);

	}

	

	public void Start() throws SQLException{

		while(true) {

			try{

				System.out.println("\n 1. Add Student 2. View All Students 3. Search with Id 4. Update Student Data 5. Delete Student 0.Exit");

				System.out.print("Choose: ");

				int choice = Integer.parseInt(sc.nextLine());

				

				switch(choice) {

				case 1:

					System.out.print("Student ID: ");

					int newid = Integer.parseInt(sc.nextLine());

					System.out.println(controller.add(readStudent(newid)) ?  "Student added." : "ID/email already exists");

					break;
 
					

				case 2:

					controller.getAll().forEach(System.out::println);

					if(controller.getAll().isEmpty()) {

						System.out.println("No Students Found!!");

					}

					break;

				

				case 3:

					System.out.println("Student ID: ");

					Student found = controller.getById(Integer.parseInt(sc.nextLine()));

					System.out.println(found == null ? "Student Not Found!!" : found);

					break;

				

				case 4:

					System.out.println("ID to Update: ");

					int updateId = Integer.parseInt(sc.nextLine());

					System.out.println(controller.update(updateId, readStudent(updateId)) ? "Student updated!!" : "Student not Found or Email already Used!!");

					break;

					

				case 5:

					System.out.println("ID to Delete: ");

					System.out.println(controller.delete(Integer.parseInt(sc.nextLine())) ? "Student Deleted!!" : "Student not Found!!");

					break;

					

				case 0:

					System.out.println("Thankyou!!");

					return;

					

				default:

					System.out.println("Choose the Valid Menu Option!!");

				}

			}

			catch(NumberFormatException e) {

				System.out.println("Enter a Valid Number!!");

			}

			catch(IllegalArgumentException e){

				System.out.println(e.getMessage());

			}

		}

	}

}