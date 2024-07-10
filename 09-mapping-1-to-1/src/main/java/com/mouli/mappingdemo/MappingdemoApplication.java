package com.mouli.mappingdemo;

import com.mouli.mappingdemo.DAO.AppDAO;
import com.mouli.mappingdemo.entity.Instructor;
import com.mouli.mappingdemo.entity.InstructorDetails;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class MappingdemoApplication {

	public static void main(String[] args) {
		SpringApplication.run(MappingdemoApplication.class, args);
	}

	@Bean
	public CommandLineRunner commandLineRunner(AppDAO appDAO) {
		return runner -> {
//			createInstructor(appDAO); //-- creates a new instructor
//			getInstructor(appDAO); -- gets the instructor

//			deleteInstructor(appDAO); // deletes the instructor

			getInstructorDetails(appDAO); // gets the instructor details
		};
	}

	public void getInstructorDetails(AppDAO appDAO) {
		// Get the instructor details object
		InstructorDetails instructorDetails = appDAO.getInstructorDetails(4);
		System.out.println("Instructor details: " + instructorDetails);
		System.out.println("Instructor: " + instructorDetails.getInstructor());
	}

	public void deleteInstructor(AppDAO appDAO) {
		// Delete the instructor object
		appDAO.deleteInstructor(1);
		System.out.println("Instructor deleted");
	}

	public void getInstructor(AppDAO appDAO) {
		// Get the instructor object
		Instructor instructor = appDAO.getInstructor(1);
		System.out.println("Instructor: " + instructor);
		System.out.println("Instructor details: " + instructor.getInstructorDetails());
	}


	public  void createInstructor(AppDAO appDAO) {
		// Create instructor object
		Instructor instructor = new Instructor("luffy", "king", "luffy@gmail.com");

		// Create instructor details object
		InstructorDetails instructorDetails = new InstructorDetails("youtube.com/luffy", "sailing");

		// Associate the objects
//		instructor.setInstructorDetails(instructorDetails);
		instructorDetails.setInstructor(instructor);

		System.out.println("Saving instructor: " + instructor);
		// Save the instructor object
		appDAO.saveInstructor(instructor);
	}

}
