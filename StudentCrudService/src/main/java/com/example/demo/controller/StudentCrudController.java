package com.example.demo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.example.demo.entity.Student;
import com.example.demo.services.StudentCrudService;

import lombok.extern.slf4j.Slf4j;

@RestController
@CrossOrigin(origins = "*")
@Slf4j
public class StudentCrudController {
	
	@Autowired
	private Environment env;
	
	@Autowired
	private StudentCrudService service;
	
	@GetMapping(path = "/students")
	public List<Student> findAll(){
		return this.service.findAll();
	}

	@RequestMapping(path = "/students/{email}")
	public void delete(@PathVariable(value="email") String email){
		this.service.delete(email);
	}
	
	@PostMapping(path = "/students", produces = "application/json", consumes = "application/json")
	public Student add(@RequestBody Student student) {
		return this.service.add(student);
	}
	
	
}
