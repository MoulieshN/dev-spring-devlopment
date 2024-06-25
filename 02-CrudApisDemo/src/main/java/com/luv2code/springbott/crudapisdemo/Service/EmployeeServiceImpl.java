package com.luv2code.springbott.crudapisdemo.Service;

import com.luv2code.springbott.crudapisdemo.DAO.EmployeeDAO;
import com.luv2code.springbott.crudapisdemo.DAO.EmployeeRepo;
import com.luv2code.springbott.crudapisdemo.Entity.Employee;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class EmployeeServiceImpl implements EmployeeService{
    private EmployeeRepo employeeRepo;

    // constructor injection
    public EmployeeServiceImpl(EmployeeRepo employeeDAO) {
        this.employeeRepo = employeeDAO;
    }

    @Override
    public List<Employee> findAll() {
        return employeeRepo.findAll();
    }

    @Transactional
    @Override
    public Employee findById(int id) {

        // Optional is a container object which may or may not contain a non-null value
        Optional<Employee> result = employeeRepo.findById(id);
        Employee employee = null;
        if (result.isPresent()) {
            employee = result.get();
        } else {
            throw new RuntimeException("Did not find employee id - " + id);
        }
        return employee;
    }

    @Transactional
    @Override
    public Employee save(Employee employee) {
        return employeeRepo.save(employee);
    }

    @Transactional
    @Override
    public void deleteById(int id) {
        employeeRepo.deleteById(id);
    }

}
