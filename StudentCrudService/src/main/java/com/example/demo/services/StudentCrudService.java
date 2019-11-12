package com.example.demo.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.entity.Student;
import com.example.demo.repos.StudentCrudRepository;

@Service
public class StudentCrudService {
	
	@Autowired
	private StudentCrudRepository repository;
	
	public Student add(Student entity) {
		return this.repository.save(entity);
	}
	
	public void delete(String email) {
		this.repository.deleteById(email);
	}
	
	public List<Student> findAll(){
		return this.repository.findAll();
	}
	
}
