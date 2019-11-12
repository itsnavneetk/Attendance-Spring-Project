package com.example.demo.entity;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "person328")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class User {
	
	@Id
	private String email;
	private String personName;
	private String password;
	private String personRole;
	
}
