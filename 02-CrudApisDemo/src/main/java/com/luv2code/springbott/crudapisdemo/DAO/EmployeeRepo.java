package com.luv2code.springbott.crudapisdemo.DAO;

import com.luv2code.springbott.crudapisdemo.Entity.Employee;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EmployeeRepo extends JpaRepository<Employee, Integer> {
}
