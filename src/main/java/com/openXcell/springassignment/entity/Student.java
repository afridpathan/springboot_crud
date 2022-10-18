package com.openXcell.springassignment.entity;

import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor(staticName = "build")
@NoArgsConstructor
@Entity
@Table(name= "student")
@EntityListeners(AuditingEntityListener.class)
public class Student {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="student_id")
	private long id;
	
	@Column(name="first_name")
	private String firstName;
	
	@Column(name="last_name")
	private String lastName;
	
	@Column(name="city_name")
	private String cityName;
	
	@Column(name="mobile_number")
	private String mobileNumber;
	
	@Column(name="pin_code")
	private int pinCode;
	
	@OneToMany (targetEntity = Subject.class, cascade = CascadeType.ALL)
	@JoinColumn (name = "student_id", referencedColumnName = "student_id")
	private List<Subject> subjects;
	
	@CreatedDate
	@Column(name = "created_date")
	private Date createdTime;

	@LastModifiedDate
	@Column(name = "last_modified_date")
	private Date lastModifiedTime;
	
	
	
}
