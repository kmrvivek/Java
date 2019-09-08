package org.luv2code.springdemo.rest;

import java.util.*;

import javax.annotation.PostConstruct;

import org.luv2code.springdemo.entity.Student;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class StudentRestController {

	private List<Student> studentsList;
	
	// @Post construct will load only once after bean creation 
	@PostConstruct
	private void loadData() {
		studentsList = new ArrayList<>();
		studentsList.add(new Student("Vivek", "Kumar"));
		studentsList.add(new Student("Rohit", "Kumar"));
		studentsList.add(new Student("Shilpa", "Kumari"));
		studentsList.add(new Student("Poonam", "Dhillon"));
	}
	
	@GetMapping("/students")
	public List<Student> getStudents(){
		return studentsList;
	}
	
	@GetMapping("/students/{studentId}")
	public Student getStudentById(@PathVariable int studentId) {
		if(studentId > studentsList.size() || studentId < 0) {
			throw new StudentNotFoundException("Student not found for id:- "+studentId );
		}
		return studentsList.get(studentId);
	}
	

}
