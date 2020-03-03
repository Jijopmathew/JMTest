package com.hsoft.testapp.controller;

import org.hamcrest.Matcher;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentMatchers;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.data.jpa.mapping.JpaMetamodelMappingContext;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import java.util.Arrays;
import java.util.List;

import com.hsoft.testapp.model.Employee;
import com.hsoft.testapp.service.EmployeeService;

@WebMvcTest(EmployeeController.class)
@MockBean(JpaMetamodelMappingContext.class)
public class EmployeeControllerTests {
	
	@Autowired
	private MockMvc mvc;

	@MockBean
	private EmployeeService employeeService;
	
	
	@Test
	public void getAllEmployeesTest() throws Exception {
		Employee employee1 = new Employee();
		employee1.setId(1l);
		employee1.setFirstName("Johns");
		employee1.setEmail("johns@gmaill.com");
		Employee employee2 = new Employee();
		employee2.setId(2l);
		employee2.setFirstName("Mathews");
		employee2.setEmail("mathews@gmaill.com");

		Mockito.when(employeeService.getAllEmployees()).thenReturn(Arrays.asList(employee1, employee2));
		mvc.perform(MockMvcRequestBuilders.get("/employees")).andExpect(model().attribute("employees", Matchers.hasSize(2)));

	}
	
	@Test
	public void createEmployeeTest() throws Exception {
		Employee employee = new Employee();
		employee.setId(1l);
		employee.setFirstName("Johns");
		employee.setEmail("johns@gmaill.com");

		Mockito.when(employeeService.saveEmployeeDetails(ArgumentMatchers.any(Employee.class))).thenReturn(employee);
		mvc.perform(MockMvcRequestBuilders.post("/employees/saveDetails")
				.contentType(MediaType.APPLICATION_FORM_URLENCODED_VALUE)
				.accept(MediaType.APPLICATION_FORM_URLENCODED_VALUE)).andExpect(redirectedUrl("/employees"));
		Employee savedEmp = employeeService.saveEmployeeDetails(employee);
		assertTrue(savedEmp.getFirstName().equals(employee.getFirstName()));

	}
	
	
	
	
	
}
