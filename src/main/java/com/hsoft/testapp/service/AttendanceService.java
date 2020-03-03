package com.hsoft.testapp.service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hsoft.testapp.model.Attendance;
import com.hsoft.testapp.model.Employee;
import com.hsoft.testapp.repository.IAttendanceRepository;
import com.hsoft.testapp.repository.IEmployeeRepository;

/**
 * This class is the service layer in between the controller and repository Do
 * the business logic in this class
 * 
 * @author Jijo
 *
 */

@Service
public class AttendanceService {

	@Autowired
	IAttendanceRepository attendanceRepository;

	@Autowired
	IEmployeeRepository employeeRepository;

	/**
	 * Get all attendance records of an employee
	 * @param employeeId
	 * @return
	 */
	public List<Attendance> getAllAttedancesByEmployeeId(Long employeeId) {

		return attendanceRepository.findByEmployeeId(employeeId);
	}
	
	/**
	 * Get all attendance records of an employee for a specific date
	 * @param employeeId
	 * @return
	 */
	public List<Attendance> getAllAttedancesOfEmployeeByDate(Long employeeId,LocalDate attendanceDate) {

		return attendanceRepository.findByIdAndAttendanceDate(employeeId,attendanceDate);
	}

	/**
	 * Create the attendance recored for both check in and check out cases
	 * @param employeeId
	 * @param attendanceType
	 * @param newAttendance
	 * @return
	 */
	public Attendance createAttendance(Long employeeId, String attendanceType, Attendance newAttendance) {

		// When the user Check in our Check out, application save the current day as
		// attendance day and time as in /out time
		
		LocalDate attendanceDate= LocalDate.now();
		LocalDateTime punchedTime=LocalDateTime.now();
		
		newAttendance.setAttendanceDate(attendanceDate);
		
		// For every check in create a new attendance recored
		if ("CHECK_IN".equalsIgnoreCase(attendanceType)) {
			newAttendance.setInTime(punchedTime);
			saveAttendanceRecord(employeeId,newAttendance);
		} else if ("CHECK_OUT".equalsIgnoreCase(attendanceType)) {
			// For checkout try to see if there is any recent checkin for the employee with
			// out any checkout, if so update the check out time on that record
			// if not create a new record with checkout time

			newAttendance.setOutTime(punchedTime);
			Attendance existingAttendance = attendanceRepository
					.findFirstByEmployeeIdAndAttendanceDateOrderByDateCreatedDesc(employeeId, newAttendance.getAttendanceDate());
			if (null != existingAttendance && null == existingAttendance.getOutTime()) {
				existingAttendance.setOutTime(newAttendance.getOutTime());
				attendanceRepository.save(existingAttendance);
			}else {
				saveAttendanceRecord(employeeId,newAttendance);
			}
		}

		return null;
	}
	
	/**
	 * Helper method to create the attendance record 
	 * @param employeeId
	 * @param newAttendance
	 * @return
	 */
	public Attendance saveAttendanceRecord(Long employeeId, Attendance newAttendance) {

		Employee emp = employeeRepository.findById(employeeId).get();
		newAttendance.setEmployee(emp);
		return attendanceRepository.save(newAttendance);
	}

}
