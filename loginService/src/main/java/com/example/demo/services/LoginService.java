package com.example.demo.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.entity.User;
import com.example.demo.repos.LoginRepository;


@Service
public class LoginService {
	
	@Autowired
	private LoginRepository repository;
	
	public List<User> findAll(){
		return this.repository.findAll();
	}
	
}
