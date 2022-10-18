package com.openXcell.springassignment.dto;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

//This Class is the DTO For Subject array of Student Payload to validate the Inputted Values

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UpdateSubjectRequest {
	@NotBlank (message = "Subject Name Should not be Empty.")
	private String subjectName;
	
	@NotNull (message = "Marks Should not be Empty.")
	private int marks;
}
