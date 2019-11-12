package com.example.demo.config;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

import com.example.demo.services.LoginService;

@Configuration
@EnableGlobalMethodSecurity( securedEnabled = true) 
public class SecurityConfig extends WebSecurityConfigurerAdapter{
	
	@Autowired
	private DataSource customDataSource;
	
	@Autowired
	LoginService service;

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		   http.authorizeRequests().antMatchers("/login").authenticated().and().formLogin();
			//web client
			http.authorizeRequests().anyRequest()
			.authenticated().and().formLogin()
			.and().logout().logoutSuccessUrl("/login")
			.invalidateHttpSession(true).deleteCookies("JSESSIONID")
			.and().csrf().disable();

	
	}

	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth.jdbcAuthentication().dataSource(this.customDataSource);
	}
}
