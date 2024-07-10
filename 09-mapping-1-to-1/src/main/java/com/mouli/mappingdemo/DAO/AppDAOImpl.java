package com.mouli.mappingdemo.DAO;

import com.mouli.mappingdemo.entity.Instructor;
import com.mouli.mappingdemo.entity.InstructorDetails;
import jakarta.persistence.EntityManager;
import jakarta.persistence.Table;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class AppDAOImpl implements AppDAO{
    // Create entity manager
    private EntityManager entityManager;


    // Inject entity manager
    @Autowired
    public AppDAOImpl(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    @Transactional // As we are doing database operations
    @Override
    public void saveInstructor(Instructor instructor) {
        // This will save the instructor object to the database and also saves the instructor details object
        // Note: As we have used CascadeType.ALL in the Instructor class, the instructor details object will also be saved
        entityManager.persist(instructor);
    }

    @Override
    public Instructor getInstructor(int id) {
        return entityManager.find(Instructor.class, id);
    }

    @Override
    @Transactional
    public void deleteInstructor(int id) {
        Instructor instructor = entityManager.find(Instructor.class, id);
        entityManager.remove(instructor);
    }

    @Override
    public InstructorDetails getInstructorDetails(int id) {
        return entityManager.find(InstructorDetails.class, id);
    }
}
