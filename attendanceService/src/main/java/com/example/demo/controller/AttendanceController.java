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
import org.springframework.core.env.Environment;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.example.demo.entity.Attendance;
import com.example.demo.services.AttendanceService;

import lombok.extern.slf4j.Slf4j;

@RestController
@CrossOrigin(origins = "*")
@Slf4j
public class AttendanceController {
	
	@Autowired
	private Environment env;
	
	@Autowired
	private AttendanceService service;
	
	//teacher
	@GetMapping(path = "/teacher/attendance")
	public List<Attendance> findAll(){
		return this.service.findAll();
	}
	
	@RequestMapping(path = "/attendance/{email}")
	public List<Attendance> findAttendanceById(@PathVariable(value="email") String email){
		return  this.service.findByEmail(email);
	}
	
	@RequestMapping(path = "/attendance/threshold/{value}")
	public List<String> findAttendanceBelowLimit(@PathVariable(value="value") int limit){
		System.out.println("Absent records : ");
		List<Attendance> attendances = this.service.findAll();
		HashMap<String, Integer> attendanceMap = new HashMap<>();
		List<String> defalutList = new ArrayList<>();
		defalutList.add("Students with absent days greater than "+limit+" :");
		
		for (Attendance attendance : attendances) {
			String email = attendance.getEmail();
			if(attendanceMap.containsKey(email)) {
				attendanceMap.put(email, attendanceMap.get(email)+1);
			}else {
				attendanceMap.put(email, 1);
			}
		}
		
		Iterator itr = attendanceMap.entrySet().iterator();  
        while (itr.hasNext()) { 
            Map.Entry email = (Map.Entry)itr.next(); 
            int daysAbsent = (int)email.getValue(); 
            if(daysAbsent>=limit) {
            	defalutList.add(email+"");
            }
        } 
        
		return defalutList;
	}
	
	
	@RequestMapping(path = "/attendance/fineReport/{month}")
	public List<String> generateFineReport(@PathVariable(value="month") int month){
		System.out.println("Absent records : ");
		List<Attendance> attendances = this.service.findAll();
		
		attendances = (attendances).stream().filter(obj -> 
		obj.getAbsentDate().getMonth()==(month)).collect(Collectors.toList());
		
		HashMap<String, Integer> attendanceMap = new HashMap<>();
		List<String> defalutList = new ArrayList<>();
		defalutList.add("Fine Report for month "+month);
		
		for (Attendance attendance : attendances) {
			String email = attendance.getEmail();
			if(attendanceMap.containsKey(email)) {
				attendanceMap.put(email, attendanceMap.get(email)+1);
			}else {
				attendanceMap.put(email, 1);
			}
		}
		
		Iterator itr = attendanceMap.entrySet().iterator();  
        while (itr.hasNext()) { 
            Map.Entry email = (Map.Entry)itr.next(); 
            int daysAbsent = ((int)email.getValue())*100; 
            defalutList.add(email+" "+daysAbsent);
            
        } 
        
		return defalutList;
	}
	
	@PostMapping(path = "/attendance", produces = "application/json", consumes = "application/json")
	public Attendance add(@RequestBody Attendance attendance) {
		return this.service.add(attendance);
	}
	
	
	//student 
	String email = "jan@g.com";
	@RequestMapping(path = "/attendance")
	public List<Attendance> findAttendanceById(){
		return  this.service.findByEmail(email);
	}
	
	@RequestMapping(path = "/attendance/monthView")
	public HashMap<String, Integer> findMonthlyAttendance(){
		System.out.println("Montly records : ");
		List<Attendance> attendances = this.service.findByEmail(email);
		HashMap<String, Integer> attendanceMap = new HashMap<>();
		
		for (Attendance attendance : attendances) {
			
			int absentMonth = attendance.getAbsentDate().getMonth();
			String monthString = new DateFormatSymbols().getMonths()[absentMonth];
			
			if(attendanceMap.containsKey(monthString)) {
				attendanceMap.put(monthString, attendanceMap.get(monthString)+1);
			}else {
				attendanceMap.put(monthString, 1);
			}
		}

		return attendanceMap;
	}
	
	@RequestMapping(path = "/attendance/maxAbsent")
	public List<String> findMaxAbsentMonth(){
		System.out.println("Month with max absent records : ");
		List<Attendance> attendances = this.service.findByEmail(email);
		HashMap<String, Integer> attendanceMap = new HashMap<>();
		
		for (Attendance attendance : attendances) {
			
			int absentMonth = attendance.getAbsentDate().getMonth();
			String monthString = new DateFormatSymbols().getMonths()[absentMonth];
			
			if(attendanceMap.containsKey(monthString)) {
				attendanceMap.put(monthString, attendanceMap.get(monthString)+1);
			}else {
				attendanceMap.put(monthString, 1);
			}
		}
		Map.Entry maxAbsentMonth = null;
		int max=0;
		List<String> resultList = new ArrayList<>();
		Iterator itr = attendanceMap.entrySet().iterator();  
        while (itr.hasNext()) { 
            Map.Entry month = (Map.Entry)itr.next(); 
            int daysAbsent = (int)month.getValue(); 
            if(daysAbsent>=max) {
            	max = daysAbsent;
            	maxAbsentMonth = month;
            }
        } 
        
        resultList.add(maxAbsentMonth+"");
		return resultList;
	}
	
	@RequestMapping(path = "/attendance/fineReport")
	public List<String> generateFineReport(){
		System.out.println("Absent records : ");
		List<Attendance> attendances = this.service.findByEmail(email);
		int month = Calendar.getInstance().get(Calendar.MONTH);
		attendances = (attendances).stream().filter(obj -> 
		obj.getAbsentDate().getMonth()==(month)).collect(Collectors.toList());
		
		List<String> defalutList = new ArrayList<>();
		defalutList.add("Fine Report for current month ");
		
		defalutList.add("Total number of absent in current month: "+attendances.size());
		defalutList.add("Total fine: "+attendances.size()*100);
		
		return defalutList;
	}

	
}
