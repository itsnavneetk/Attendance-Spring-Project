package com.example.demo.entity;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "student28")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Student {
	
	@Id
	private String email;
	private String studentName;
	private long phoneNumber;
	private String address;
	
}
