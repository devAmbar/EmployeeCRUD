package com.crud.controller;

import java.util.List;
import java.util.Optional;

import javax.websocket.server.PathParam;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.crud.entity.Employee;
import com.crud.exception.EmployeeNotFound;
import com.crud.service.EmployeeService;

@RestController
@RequestMapping("/employee")
public class EmployeeController {
	
	@Autowired
	private EmployeeService employeeService;
	
	@GetMapping("/home")
	public ResponseEntity<?> home() {
		return new ResponseEntity("Welcome to the Employee page",HttpStatus.OK);
	}
	
	@PostMapping("/save")
	public ResponseEntity<Employee> saveEmployee(@RequestBody Employee employee) {
		Employee e = this.employeeService.saveEmployee(employee);
		return new ResponseEntity(e, HttpStatus.CREATED);
	}
	
	@GetMapping("/all")
	public ResponseEntity<?> getAllEmployee() throws EmployeeNotFound{ 
		List<Employee> employees = this.employeeService.getAllEmployee();
		if(employees.size() == 0) {
			throw new EmployeeNotFound("No Employee Details");
		}
		return new ResponseEntity(employees,HttpStatus.OK);
	}
	
	@GetMapping("/byId/{id}")
	public ResponseEntity<Employee> getEmployeeById(@PathVariable Long id) throws EmployeeNotFound{
		Employee employee =  this.employeeService.findEmployeeById(id).orElseThrow(() -> new EmployeeNotFound("Employee Not found"));
		return ResponseEntity.ok(employee);
	}
	
	@PutMapping("/{id}")
	public ResponseEntity<Employee> updateEmployeeById(@PathVariable Long id, @RequestBody Employee emp) throws EmployeeNotFound {
		Employee e = this.employeeService.findEmployeeById(id).orElseThrow(() -> new EmployeeNotFound("Employee Not Found"));
		
		Employee employee =  this.employeeService.updateEmployeeById(id, emp);
		return new ResponseEntity(employee,HttpStatus.CREATED);
		
	}
	
	@DeleteMapping("/{id}")
	public String deleteEmployee(@PathVariable Long id) throws EmployeeNotFound {
		Employee e = this.employeeService.findEmployeeById(id).orElseThrow(() -> new EmployeeNotFound("Employee Not present"));
		try {
			this.employeeService.deleteEmployee(id);
			String message = "Employee with id : " + id + " is deleted.";
			return message;
		} catch (Exception e2) {
			return null;
		}
		
		
	}
	
}
