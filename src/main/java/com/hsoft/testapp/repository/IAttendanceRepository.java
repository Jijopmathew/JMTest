package com.hsoft.testapp.repository;

import java.time.LocalDate;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.hsoft.testapp.model.Attendance;

public interface IAttendanceRepository extends JpaRepository<Attendance, Long> {
	
	List<Attendance> findByEmployeeId(Long employeeId);
	
	Attendance findFirstByEmployeeIdAndAttendanceDateOrderByDateCreatedDesc(Long employeeId,LocalDate attendanceDate);
	
	List<Attendance> findByIdAndAttendanceDate(Long employeeId, LocalDate attendanceDate);	
	

}
