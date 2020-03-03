package com.hsoft.testapp.model;

import java.time.LocalDateTime;


import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity
@Table(name = "employee")
@EntityListeners(AuditingEntityListener.class)
@JsonIgnoreProperties(
        value = {"createdBy", "modifiedBy"},
        allowGetters = true
        )
public class Employee {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)	
	private Long id;

	private String firstName;

	private String lastName;

	private String email;

	private String desig;

	@CreatedDate
	private LocalDateTime dateCreated;
	
	@LastModifiedDate
    private LocalDateTime dateModified;
	
	@CreatedBy
	private String createdBy;
	
	@LastModifiedBy
	private String modifiedBy;
	
	
	
	
	public Long getId() {
		return id;
	}




	public void setId(Long id) {
		this.id = id;
	}




	public String getFirstName() {
		return firstName;
	}




	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}




	public String getLastName() {
		return lastName;
	}




	public void setLastName(String lastName) {
		this.lastName = lastName;
	}




	public String getEmail() {
		return email;
	}




	public void setEmail(String email) {
		this.email = email;
	}




	public String getDesig() {
		return desig;
	}




	public void setDesig(String desig) {
		this.desig = desig;
	}




	public LocalDateTime getDateCreated() {
		return dateCreated;
	}




	public void setDateCreated(LocalDateTime dateCreated) {
		this.dateCreated = dateCreated;
	}




	public LocalDateTime getDateModified() {
		return dateModified;
	}




	public void setDateModified(LocalDateTime dateModified) {
		this.dateModified = dateModified;
	}




	public String getCreatedBy() {
		return createdBy;
	}




	public void setCreatedBy(String createdBy) {
		this.createdBy = createdBy;
	}




	public String getModifiedBy() {
		return modifiedBy;
	}




	public void setModifiedBy(String modifiedBy) {
		this.modifiedBy = modifiedBy;
	}




	@Override
	public String toString() {

		return "Employee [Id : " + getId() + " , First Name : " + getFirstName() + " , Last Name : " + getLastName()
				+ " , Email " + getEmail() + " , Designation : " + getDesig()+" ]";
	}

}
