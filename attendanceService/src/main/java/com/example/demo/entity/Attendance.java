package com.example.demo.entity;

import java.sql.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "attendace28")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Attendance {
	
	@Id 
	private long id;
	private String email;
	private Date absentDate;
	
}
