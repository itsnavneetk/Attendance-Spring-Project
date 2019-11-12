package com.example.demo.repos;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.example.demo.entity.Student;

@Repository
public interface StudentCrudRepository extends JpaRepository<Student, String>{

}
