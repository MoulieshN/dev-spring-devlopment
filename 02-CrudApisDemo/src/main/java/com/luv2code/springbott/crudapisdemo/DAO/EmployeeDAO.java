package com.luv2code.springbott.crudapisdemo.DAO;

import com.luv2code.springbott.crudapisdemo.Entity.Employee;

import java.util.List;

public interface EmployeeDAO {
    List<Employee> findAll();

    Employee findById(int id);

    Employee save(Employee employee);

    void deleteById(int id);
}
