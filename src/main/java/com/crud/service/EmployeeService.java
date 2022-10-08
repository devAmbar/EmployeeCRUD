package com.crud.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.crud.entity.Employee;
import com.crud.repository.EmployeeRepository;

@Service
public class EmployeeService {
	
	@Autowired
	private EmployeeRepository repository;
	
	public Employee saveEmployee(Employee employee) {
		Employee e = this.repository.save(employee);
		System.out.println(e.toString());
		return e;
	}

	public List<Employee> getAllEmployee() {
		return this.repository.findAll();
		
	}

	public Optional<Employee> findEmployeeById(Long id) {
		return this.repository.findById(id);
	}

	public Employee updateEmployeeById(Long id, Employee emp) {
		Employee e = this.repository.findById(id).get();
		e.setEmpName(emp.getEmpName());
		e.setDepartment(emp.getDepartment());
		e.setEmail(emp.getEmail());
		e.setSalary(emp.getSalary());
		this.repository.save(e);
		return e;
	}

	public void deleteEmployee(Long id) {
		 this.repository.deleteById(id);
	}

}
