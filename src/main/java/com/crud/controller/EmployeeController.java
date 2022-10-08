package com.crud.controller;

import java.util.List;
import java.util.Optional;

import javax.websocket.server.PathParam;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.crud.entity.Employee;
import com.crud.service.EmployeeService;

@RestController
@RequestMapping("/employee")
public class EmployeeController {
	
	@Autowired
	private EmployeeService employeeService;
	
	@GetMapping("/home")
	public String home() {
		return "Welcome to the Employee page";
	}
	
	@PostMapping("/mm")
	public Employee saveEmployee(@RequestBody Employee employee) {
		Employee e = this.employeeService.saveEmployee(employee);
		return e;
	}
	
	@GetMapping("/all")
	public List<Employee> getAllEmployee(){
		return this.employeeService.getAllEmployee();
	}
	
	@GetMapping("/byId/{id}")
	public Optional<Employee> getEmployeeById(@PathVariable Long id) {
		return this.employeeService.findEmployeeById(id);
	}
	
	@PutMapping("/{id}")
	public Employee updateEmployeeById(@PathVariable Long id, @RequestBody Employee emp) {
		System.out.println(id);
		System.out.println(emp);
		return this.employeeService.updateEmployeeById(id, emp);
	}
	
	@DeleteMapping("/{id}")
	public String deleteEmployee(@PathVariable Long id) {
		this.employeeService.deleteEmployee(id);
		String s = "Employee with id : " + id + " is deleted.";
		return s;
	}
	
}
