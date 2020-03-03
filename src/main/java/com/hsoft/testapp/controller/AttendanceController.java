package com.hsoft.testapp.controller;

import java.time.LocalDate;
import java.util.List;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.format.annotation.DateTimeFormat.ISO;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import com.hsoft.testapp.model.Attendance;
import com.hsoft.testapp.service.AttendanceService;

/**
 * Rest Controller class for Attendance
 * 
 * @author Jijo
 *
 */

@RestController
public class AttendanceController {

	@Autowired
	AttendanceService attendanceService;

	/**
	 * Create Employee attendance. Both Check In and Check out
	 * 
	 * @param employeeId
	 * @param attendanceType
	 * @param attendance
	 * @return
	 */
	@PostMapping(path = "/employees/{employeeId}/attendances/{type}", consumes = "application/x-www-form-urlencoded")
	public ModelAndView createAttendance(@PathVariable(value = "employeeId") Long employeeId,
			@PathVariable(value = "type") String attendanceType, Attendance attendance) {
		attendanceService.createAttendance(employeeId, attendanceType, attendance);
		return new ModelAndView("redirect:/employees");

	}
	  
	 

	// Following methods are not for this sprint added for testing but useful for future sprints
	
	/**
	 * Get All attendance records of the employee
	 * 
	 * @param id
	 * @return
	 */
	@GetMapping("/employees/{employeeId}/attendances")
	List<Attendance> getAllAttedancesByEmployeeId(@PathVariable(value = "employeeId") Long employeeId) {
		return attendanceService.getAllAttedancesByEmployeeId(employeeId);
	}

	/**
	 * Get the attendances records of employee for the date
	 * 
	 * @param employeeId
	 * @param attendanceDate
	 * @return
	 */
	@GetMapping("/employees/{employeeId}/attendances/{date}")
	List<Attendance> getEmployeeAttedancesByDate(@PathVariable(value = "employeeId") Long employeeId,
			@PathVariable(value = "date") @DateTimeFormat(iso = ISO.DATE) LocalDate attendanceDate) {
		return attendanceService.getAllAttedancesOfEmployeeByDate(employeeId, attendanceDate);
	}

}
