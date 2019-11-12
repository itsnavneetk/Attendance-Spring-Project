package com.example.demo.controller;

import java.sql.Date;
import java.text.DateFormatSymbols;
import java.time.Month;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.loadbalancer.LoadBalancerClient;
import org.springframework.core.env.Environment;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.example.demo.entity.Attendance;
import com.example.demo.entity.Student;

import lombok.extern.slf4j.Slf4j;

@RestController
@CrossOrigin(origins = "*")
@Slf4j
public class AttendanceClientController {
	

	@Autowired
	private RestTemplate template;
	
	//crud
	@GetMapping(path = "/students")
	public String findAll(){
		String attendance = template.getForObject("http://student-crud-service/students", String.class);
		return attendance;
	}

	@RequestMapping(path = "/students/{email}")
	public String delete(@PathVariable(value="email") String email){
		String attendance = template.getForObject("http://student-crud-service/students"+email, String.class);
		return attendance;
	}
		
	@PostMapping(path = "/students", produces = "application/json", consumes = "application/json")
	public String add(@RequestBody Student student) {
		String studentStr = template.postForObject("http://student-crud-service/students", student, String.class);
		return studentStr;
	}
	
	//teacher
	@GetMapping(path = "/teacher/attendance")
	public String students() {
		String attendance = template.getForObject("http://attendance-service/teacher/attendance", String.class);
		return attendance;
	}
	
	@RequestMapping(path = "/attendance/{email}")
	public String getStudentsByEmail(@PathVariable(value="email") String email) {
		String attendance = template.getForObject("http://attendance-service/attendance/"+email, String.class);
		return attendance;
	}
	
	@RequestMapping(path = "/attendance/threshold/{value}")
	public String findAttendanceBelowLimit(@PathVariable(value="value") int limit){
		String attendance = template.getForObject("http://attendance-service/attendance/threshold/"+limit, String.class);
		return attendance;
	}
	
	@RequestMapping(path = "/attendance/fineReport/{month}")
	public String generateFineReport(@PathVariable(value="month") int month){
		String attendance = template.getForObject("http://attendance-service/attendance/fineReport/"+month, String.class);
		return attendance;
	}
	
	@PostMapping(path = "/attendance", produces = "application/json", consumes = "application/json")
	public String add(@RequestBody Attendance attendance) {
		String attendanceStr = template.postForObject("http://attendance-service/attendance", attendance, String.class);
		return attendanceStr;
	}
	
	//student
	@GetMapping(path = "/attendance")
	public String findAttendanceById(){
		String attendance = template.getForObject("http://attendance-service/attendance", String.class);
		return attendance;
	}
	
	@RequestMapping(path = "/attendance/monthView")
	public String findMonthlyAttendance(){
		String attendance = template.getForObject("http://attendance-service/attendance/monthView", String.class);
		return attendance;
	}
	
	@RequestMapping(path = "/attendance/maxAbsent")
	public String findMaxAbsentMonth(){
		String attendance = template.getForObject("http://attendance-service/attendance/maxAbsent", String.class);
		return attendance;
	}
	
	@RequestMapping(path = "/attendance/fineReport")
	public String generateFineReport(){
		String attendance = template.getForObject("http://attendance-service/attendance/fineReport", String.class);
		return attendance;
	}
}

