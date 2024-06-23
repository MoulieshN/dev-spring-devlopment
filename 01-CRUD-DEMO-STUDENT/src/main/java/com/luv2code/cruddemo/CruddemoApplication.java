package com.luv2code.cruddemo;

import com.luv2code.cruddemo.Entity.Student;
import com.luv2code.cruddemo.dao.StudentDAO;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.List;

@SpringBootApplication
public class CruddemoApplication {

	public static void main(String[] args) {

		SpringApplication.run(CruddemoApplication.class, args);
	}


	// Note: Executed after the spring beans have been loaded
	@Bean
	public CommandLineRunner commandLineRunner(StudentDAO studentDAO) {
		return runner -> {
//			createSaveStudent(studentDAO);
//
//			System.out.println("Reading the student: "+readbyId(studentDAO));
//
//			queryTheStudents(studentDAO);

//			getStudentsFindByFirstName(studentDAO);

//			updateStudentById(studentDAO);

//			updateMultipleStudentObject(studentDAO);

//			updateStudentByPlaceholder(studentDAO);

			queryForDeletingStudents(studentDAO);
		};
	}

	private void queryForDeletingStudents(StudentDAO studentDAO) {
		// Delete students
		System.out.println("------------------------------- Deleting students ------------------------");
//		studentDAO.deleteById(1);
		studentDAO.deleteAll(List.of(2, 3));
	}

	private void updateStudentByPlaceholder(StudentDAO studentDAO) {
		// Update a student by query
		System.out.println("------------------------------- Updating student with id 1 by query ------------------------");
		studentDAO.updateByQuery(1, "first.cry@gmail.com");
	}

	private void updateMultipleStudentObject(StudentDAO studentDAO) {
		// Update multiple students
		System.out.println("------------------------------- Updating multiple students ------------------------");
		studentDAO.updateMultipleStudents();
	}

	private void updateStudentById(StudentDAO studentDAO) {
		// Update a student
		System.out.println("------------------------------- Updating student with id 1 ------------------------");
		studentDAO.update(1);

	}

	private void getStudentsFindByFirstName(StudentDAO studentDAO) {
		// Get students by first name
		System.out.println("------------------------------- Getting students by first name ------------------------");
		studentDAO.findByFirstName("Chandra").forEach(System.out::println);

	}

	private void queryTheStudents(StudentDAO studentDAO) {
		// Get all students
		System.out.println("------------------------------- Getting all students ------------------------");
		studentDAO.findAll().forEach(System.out::println);
	}

	private void createSaveStudent(StudentDAO studentDAO) {
		// Create a student
		System.out.println("-------------------------------- Creating a new student ------------------------v");
		Student student = new Student("Super", "Man", "superman@gmail.com");

		// Save the student
		studentDAO.save(student);

		// Get the student id
		int studentId = student.getId();
		System.out.println("-------------------  Saved student. Generated id: " + studentId+ " ------------------------");
	}

	private Student readbyId(StudentDAO studentDAO) {
		// Get a student by id
		System.out.println("------------------------------- Getting student with id 1 ------------------------");
		Student student = studentDAO.findById(1);
		System.out.println("Student: " + student);
		return student;
	}

}
