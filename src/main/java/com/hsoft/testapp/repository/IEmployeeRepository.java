package com.hsoft.testapp.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.hsoft.testapp.model.Employee;

public interface IEmployeeRepository extends JpaRepository<Employee, Long>{

}
