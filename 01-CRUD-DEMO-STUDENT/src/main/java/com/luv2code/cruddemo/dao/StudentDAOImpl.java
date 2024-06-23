package com.luv2code.cruddemo.dao;

import com.luv2code.cruddemo.Entity.Student;
import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class StudentDAOImpl implements StudentDAO {
    // Define entity manager
    // Note: We use EntityManager as a general-purpose DAO interface for managing lifecycle of entity instances, such as:
    // a. Create & Remove persistent entity instances
    // b. Find entities by their primary key
    // c. Query over entities
    private EntityManager entityManager;

    // Inject entity manager
    @Autowired
    public StudentDAOImpl(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    // Save the student
    @Transactional
    @Override
    public void save(Student student) {
        entityManager.persist(student);
    }

    @Override
    public Student findById(Integer id) {
        //                        entity.class, primary key
        return entityManager.find(Student.class, id);
    }

    @Override
    public List<Student> findAll() {
        // TypedQuery is used to create a query
        TypedQuery<Student> query = entityManager.createQuery("from student", Student.class);
        return query.getResultList();
    }

    @Override
    public List<Student> findByFirstName(String firstName) {
        TypedQuery<Student> query = entityManager.createQuery("from student s where s.firstName = :firstName", Student.class);

        // Placeholder for the parameter
        query.setParameter("firstName", firstName);
        return query.getResultList();
    }

    @Transactional
    @Override
    public void update(Integer id) {
        Student student = entityManager.find(Student.class, id);
        student.setFirstName("Batman");

        // Has to be called to update the student, otherwise it won't be updated
        entityManager.merge(student);
        System.out.println("Updated student: " + student);
        return;
    }

    @Transactional
    @Override
    public void updateMultipleStudents() {
        // Update multiple students
        int noOfRowsUpdated = entityManager.createQuery("update student s set s.firstName = 'Superman'")
                .executeUpdate();

        System.out.println("Updated multiple students: ==== " + noOfRowsUpdated + " ====");

    }

    @Transactional
    @Override
    public void updateByQuery(Integer id, String email) {
        entityManager.createQuery("update student s set s.email = :email where s.id = :id")
                .setParameter("email", email)
                .setParameter("id", id)
                .executeUpdate();
    }

    @Transactional
    @Override
    public void deleteById(Integer id) {
        int noOfRowsDeleted = entityManager.createQuery("delete from student s where s.id = :id")
                .setParameter("id", id)
                .executeUpdate();
    }

    @Transactional
    @Override
    public void deleteAll(List<Integer> ids) {
        entityManager.createQuery("delete from student s where s.id in (:ids) ")
                .setParameter("ids", ids)
                .executeUpdate();
    }

}