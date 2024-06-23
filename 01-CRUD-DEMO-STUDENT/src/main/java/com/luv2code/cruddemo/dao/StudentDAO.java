package com.luv2code.cruddemo.dao;

import com.luv2code.cruddemo.Entity.Student;

import java.util.List;

public interface StudentDAO {
    void save(Student student);


    Student findById(Integer id);

    List<Student> findAll();

    List<Student> findByFirstName(String firstName);

    void update(Integer id);

    void updateMultipleStudents();

    void updateByQuery(Integer id, String email);

    void deleteById(Integer id);

    void deleteAll(List<Integer> ids);

}
