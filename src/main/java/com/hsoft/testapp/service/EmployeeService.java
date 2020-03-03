package com.hsoft.testapp.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.hsoft.testapp.model.Employee;
import com.hsoft.testapp.repository.IEmployeeRepository;

/**
 * This  class is the service layer in between the controller and repository
 * Do the business logic in this class
 * @author Jijo
 *
 */

@Service
public class EmployeeService {

	@Autowired
	IEmployeeRepository repository;
	
	
	public List<Employee> getAllEmployees() {
		return repository.findAll();
	}
	
	public Employee getEmployeeById(Long id) {
		return repository.findById(id).get();
	}
	
	
	public Employee saveEmployeeDetails(Employee newEmployee) {
		if (null == newEmployee.getId()) {
			return repository.save(newEmployee);
		} else {
			repository.findById(newEmployee.getId()).map(employee -> {
				employee.setFirstName(newEmployee.getFirstName());
				employee.setLastName(newEmployee.getLastName());
				employee.setEmail(newEmployee.getEmail());
				employee.setDesig(newEmployee.getDesig());
				return repository.save(employee);
			});
		}
		return null;
	}
		
	
	public void deleteEmployee(Long id) {
		repository.deleteById(id);
	}

}
