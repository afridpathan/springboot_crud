package com.openXcell.springassignment.service;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;

import com.openXcell.springassignment.dto.StudentRequest;
import com.openXcell.springassignment.dto.SubjectRequest;
import com.openXcell.springassignment.dto.UpdateStudentRequest;
import com.openXcell.springassignment.dto.UpdateSubjectRequest;
import com.openXcell.springassignment.entity.Student;
import com.openXcell.springassignment.entity.Subject;
import com.openXcell.springassignment.exception.CustomException;
import com.openXcell.springassignment.repository.StudentRepository;

@Service
public class StudentService {
	
	@Autowired
	private StudentRepository studentRepository;
	
	public Student addStudent (StudentRequest studentRequest) {
		
		List<Subject> subjects = new ArrayList<Subject> ();
		
		//Preparing the Subject Object List to be passed in Student Constructor
		studentRequest.getSubjects().forEach(s ->
		{
			Subject subject = Subject.build(0, s.getSubjectName(), s.getMarks());
			subjects.add(subject);
		});
		
		//Passing Values to Student (Entity) Class from DTO Object
		Student student = Student.build(0, studentRequest.getFirstName(), studentRequest.getLastName()
				, studentRequest.getCityName(), studentRequest.getMobileNumber(), Integer.valueOf(studentRequest.getPinCode())
				, subjects,null,null);
		
		return studentRepository.save(student);
		
		
	}

	public List<Student> getStudents() {
		return studentRepository.findAll();
	}

	public Student getStudentById(long id) throws CustomException {
		Student student = studentRepository.findById(id).orElse(null);
		if (student != null)
			return student;
		else
			throw new CustomException("Student not found with Id : "+id);
		
	}
	
	public String deleteStudent(long id) throws CustomException {
		Student student = studentRepository.findById(id).orElse(null);
		if (student != null) {
			validateForDelete(student);
			studentRepository.deleteById(id);
		}
		else {
			throw new CustomException("Student not found with Id :"+id);
		}
		
		return "Student with ID :" +id + " has been deleted.";
	}
	
	static void validateForDelete (Student student) throws CustomException {
		
		//validation for Subject is English and marks is morethan 70
		for (Subject subject : student.getSubjects())
		{
			if (subject.getSubjectName().equalsIgnoreCase("English") && subject.getMarks() > 70)
				throw new CustomException("Student with English Subject and Marks morethan 70 Can't be deleted.");
		}
	}

	public Student updateStudent(long id, UpdateStudentRequest updateStudentRequest) throws CustomException {
		Student student = studentRepository.findById(id).orElse(null);
		if (student != null)
		{
			updateStudentObject(student, updateStudentRequest);
			return studentRepository.save(student);
		}
		else
		{
			throw new CustomException("Student not found with Id : "+id);
		}
	}
	
	static void updateStudentObject (Student student, UpdateStudentRequest updateStudentRequest) {
		if (updateStudentRequest.getFirstName() != null)
			student.setFirstName(updateStudentRequest.getFirstName());
		if (updateStudentRequest.getLastName() != null)
			student.setLastName(updateStudentRequest.getLastName());
		if (updateStudentRequest.getCityName() != null)
			student.setCityName(updateStudentRequest.getCityName());
		if (updateStudentRequest.getMobileNumber() != null)
			student.setMobileNumber(updateStudentRequest.getMobileNumber());
		if (updateStudentRequest.getPinCode() != null)
			student.setPinCode(Integer.valueOf(updateStudentRequest.getPinCode()));
		
		if (updateStudentRequest.getSubjects() != null)
		{
			List<Subject> newAddedSubjectList = new ArrayList<>();
			for (UpdateSubjectRequest request : updateStudentRequest.getSubjects()) {
				for (int i = 0; i < student.getSubjects().size(); i++) {
					if (student.getSubjects().get(i).getSubjectName().equals(request.getSubjectName())) {
						student.getSubjects().get(i).setMarks(request.getMarks());
					}
					else
					{
						Subject sub = Subject.build(0, request.getSubjectName(), request.getMarks());
						newAddedSubjectList.add(sub);
					}
				}
			}
			
			if (!newAddedSubjectList.isEmpty())
				student.getSubjects().addAll(newAddedSubjectList);
			
		}
	}

	public Page<Student> getStudentDataCustom(Integer pageNo) {
		if (pageNo == null)
			pageNo = 1;
		
		Pageable page = PageRequest.of(pageNo, 5,Sort.by(Direction.ASC, "student_id"));
		return studentRepository.getStudentsCustom(page);
	}
	
	

}
