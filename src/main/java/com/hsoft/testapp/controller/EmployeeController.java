package com.hsoft.testapp.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;



import com.hsoft.testapp.model.Employee;
import com.hsoft.testapp.service.EmployeeService;



/**
 *  Controller class for employee actions
 *  This includes the following functionalities
 *  1. Create New Employee
 *  2. Update Existing Employee
 *  3. Remove Existing Employee
 *  4. List All the Employees
 * @author Jijo
 *
 */

@Controller
public class EmployeeController {
	
	@Autowired
	EmployeeService employeeService;
		
	
	/**
	 * Get All Employees.  This will launch  employee list page with data if any
	 * @return
	 */
	@GetMapping(value = "/employees")
    public String getAllEmployees(Model model) {
		List<Employee> empList= employeeService.getAllEmployees();
		model.addAttribute("employees", empList);
		return "list-employees";
    }
	
		
		
	/**
	 * Method to launch the add page
	 * @param model
	 * @return
	 */
	@GetMapping(value = "/employees/add")
    public String getAddEmployeesPage(Model model) {
		model.addAttribute("employee", new Employee());
		return "add-edit-employee";
    }
	
	
	/**
	 * Method to launch the edit page
	 * @param model
	 * @return
	 */
	@GetMapping(value = "/employees/edit/{id}")
    public String getEditEmployeesPage(Model model,@PathVariable Long id) {
		Employee emp=employeeService.getEmployeeById(id);
		model.addAttribute("employee",emp);	
		return "add-edit-employee";
    }
	
	
	/**
	 * Save the employee details. This mapping is used for both add and edit actions 
	 * @param newEmployee
	 * @return
	 */
	
	@PostMapping(path = "/employees/saveDetails", consumes = "application/x-www-form-urlencoded")
	public String createEmployee(Employee newEmployee) {
		employeeService.saveEmployeeDetails(newEmployee);
		return "redirect:/employees";
	}
	
	
 
	/**
	 * Delete employee
	 * @param id
	 */
	@GetMapping("/employees/delete/{id}")
    public String deleteEmployee(@PathVariable Long id) {
    	employeeService.deleteEmployee(id);
    	return "redirect:/employees";
    }
    

	

}
