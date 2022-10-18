package com.openXcell.springassignment.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor (staticName = "build")
@NoArgsConstructor
@Entity
@Table(name= "subject")
public class Subject {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="subject_id")
	private long id;
	
	@Column(name="subjectName")
	private String subjectName;
	
	@Column(name="marks")
	private int marks;
	
	
	
	
}
