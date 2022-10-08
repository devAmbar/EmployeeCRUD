package com.crud.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.crud.entity.Employee;

public interface EmployeeRepository extends JpaRepository<Employee, Long>{
	
 Optional<Employee> findById(Long id);

void save(Optional<Employee> e);
 
}
