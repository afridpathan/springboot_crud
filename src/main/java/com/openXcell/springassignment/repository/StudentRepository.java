package com.openXcell.springassignment.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.openXcell.springassignment.entity.Student;

public interface StudentRepository extends JpaRepository<Student, Long>{
	
	@Query (value ="select * from student where student_id in (select student_id \r\n"
			+ "from subject where subject_name = 'English' and marks > 70)",
			countQuery = "select count(*) from student where student_id in (select student_id \r\n"
					+ "from subject where subject_name = 'English' and marks > 30)", nativeQuery = true)
	Page<Student> getStudentsCustom (Pageable pageable);
}
