package com.example.demo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.entity.User;
import com.example.demo.services.LoginService;

import lombok.extern.slf4j.Slf4j;

@RestController
@CrossOrigin(origins = "*")
@Slf4j
public class LoginController {
	
	@Autowired
	private Environment env;
	
	@Autowired
	private LoginService service;
	
	@GetMapping(path = "/login")
//	@Secured("ROLE_TEACHER")
	public List<User> findAll(){
		System.out.println("request received!");
		return this.service.findAll();
	}
}
