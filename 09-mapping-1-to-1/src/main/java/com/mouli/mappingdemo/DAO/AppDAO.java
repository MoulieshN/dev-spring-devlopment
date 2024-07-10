package com.mouli.mappingdemo.DAO;

import com.mouli.mappingdemo.entity.Instructor;
import com.mouli.mappingdemo.entity.InstructorDetails;


public interface AppDAO {
    public void saveInstructor(Instructor instructor);

    public Instructor getInstructor(int id);

    public void deleteInstructor(int id);

    public InstructorDetails getInstructorDetails(int id);
}
