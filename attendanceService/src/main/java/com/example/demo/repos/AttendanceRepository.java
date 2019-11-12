package com.example.demo.repos;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.example.demo.entity.Attendance;

@Repository
public interface AttendanceRepository extends JpaRepository<Attendance, String>{
	public List<Attendance> findByEmail(String email);
}
