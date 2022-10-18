package com.openXcell.springassignment.dto;

import java.util.List;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

import org.hibernate.validator.constraints.UniqueElements;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

//This Class is the DTO For Student Payload to validate the Inputted Values

@Data
@AllArgsConstructor (staticName = "build")
@NoArgsConstructor
public class StudentRequest {
	
	@NotBlank (message = "First Name Should not be Empty.")
	private String firstName;
	
	@NotBlank (message = "Last Name Should not be Empty.")
	private String lastName;
	
	@NotBlank (message = "City Name Should not be Empty.")
	private String cityName;
	
	@NotBlank (message = "Mobile Number Should not be Empty.")
	@Pattern (regexp = "^\\d{10}$", message = "Invalid Mobile Number, It should contain 10 Digits")
	private String mobileNumber;
	
	@NotNull (message = "Pin Code Should not be Empty.")
	@Pattern (regexp = "^\\d{6}$", message = "Invalid Pin Code, It should contain 6 Digits")
	private String pinCode;
	
	@Valid
	@UniqueElements (message = "Duplicate Subjects Found.")
	private List<SubjectRequest> subjects;

}
