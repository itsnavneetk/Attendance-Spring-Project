package com.example.demo.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.entity.Attendance;
import com.example.demo.repos.AttendanceRepository;

@Service
public class AttendanceService {
	
	@Autowired
	private AttendanceRepository repository;
	
	public Attendance add(Attendance entity) {
		return this.repository.save(entity);
	}
	
	public List<Attendance> findByEmail(String email) {
		return this.repository.findByEmail(email);
	}
	
	public List<Attendance> findAll(){
		return this.repository.findAll();
	}
	
}
