package com.openXcell.springassignment.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.openXcell.springassignment.dto.StudentRequest;
import com.openXcell.springassignment.dto.UpdateStudentRequest;
import com.openXcell.springassignment.entity.Student;
import com.openXcell.springassignment.exception.CustomException;
import com.openXcell.springassignment.repository.StudentRepository;
import com.openXcell.springassignment.service.StudentService;

@RestController
public class StudentController {
	
	@Autowired
	private StudentService studentService;
	
	@PostMapping ("/addStudent")  //Problem 2
	public ResponseEntity<Student> addStudent (@RequestBody @Valid StudentRequest studentRequest)
	{
		return new ResponseEntity<>(studentService.addStudent(studentRequest),HttpStatus.CREATED);
	}
	
	@GetMapping("/students")
    public List<Student> findAllStudents() {
        return studentService.getStudents();
    }

    @GetMapping("/students/{id}")  //Problem 3
    public Student findStudentById(@PathVariable long id) throws CustomException {
        return studentService.getStudentById(id);
    }
    
    @DeleteMapping("/student/delete/{id}")  //Problem 4
    public ResponseEntity<String> deleteStudent (@PathVariable long id) throws CustomException {
    	return new ResponseEntity<String>(studentService.deleteStudent(id),HttpStatus.OK);
    }
    
    @PutMapping("/student/update/{id}")  //Problem 5
    public ResponseEntity<Student> updateStudent (@PathVariable long id, 
    		@RequestBody @Valid UpdateStudentRequest updateStudentRequest) throws CustomException
    {
    	return new ResponseEntity<>(studentService.updateStudent(id, updateStudentRequest),HttpStatus.OK);
    }
    
    @GetMapping("/student/getDataProblem6")   //Problem 6
    public Page<Student> findStudentProblem6(
    		@RequestParam(value = "pageNo", required = false) Integer pageNo) {
        return studentService.getStudentDataCustom(pageNo);
    }
	

}
