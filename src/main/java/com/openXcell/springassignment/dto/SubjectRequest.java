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
public class SubjectRequest {
	@NotBlank (message = "Subject Name Should not be Empty.")
	private String subjectName;
	
	@NotNull (message = "Marks Should not be Empty.")
	private int marks;
	
	@Override
	public int hashCode() {
	    return subjectName.hashCode();
	}

	@Override
	public boolean equals(Object obj) {
	    if (obj instanceof SubjectRequest) {
	        return subjectName.equals(((SubjectRequest)obj).subjectName);
	    }
	    return false;
	}
}
