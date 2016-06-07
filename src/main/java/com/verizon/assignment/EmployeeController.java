package com.verizon.assignment;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class EmployeeController {

	@Autowired
	private EmployeeRepository employeeRepository;

	@Autowired
	private DepartmentRepository departmentRepository;

	@RequestMapping("/employee/query")
	public String query(@RequestParam(value = "lastName") String lastName) {
		return employeeRepository.findByLastName(lastName).toString();

	}

	@RequestMapping("/employee/save")
	public String save(
			@RequestParam(value = "firstName") String firstName,
			@RequestParam(value = "lastName") String lastName,
			@RequestParam(value = "salary") double salary,
			@RequestParam(required = false, value = "department") Long departmentId,
			@RequestParam(required = false, value = "manager") Long managerId) {

		Department department = departmentId != null ? departmentRepository
				.findOne(departmentId) : null;
		Employee manager = managerId != null ? employeeRepository
				.findOne(managerId) : null;
		Employee employee = new Employee(firstName, lastName, salary,
				department, manager);
		employeeRepository.save(employee);
		return "Employee saved: " + employee.toString();
	}

	@RequestMapping("/employee/update")
	public String update(@RequestParam(value = "id") long id,
			@RequestParam(value = "salary") double salary) {

		Employee employee = employeeRepository.findOne(id);
		if (employee != null) {
			employee.setSalary(salary);
			;
			employeeRepository.save(employee);
			return "Employee saved: " + employee.toString();
		} else {
			return "Employee not found for id " + id;
		}
	}

	@RequestMapping(value = "/employee/queryWithId")
	public String findOne(@RequestParam(value = "id") long id) {
		// TODO Auto-generated method stub
		Employee findOne = employeeRepository.findOne(id);
		return findOne == null ? "Not Found" : findOne.toString();
	}


	@RequestMapping(value = "/employee/queryAll")
	public String findAll() {
		// TODO Auto-generated method stub
		return employeeRepository.findAll().toString();
	}

	
	@RequestMapping(value = "/employee/delete")
	public String delete(@RequestParam(value = "id") long id) {
		// TODO Auto-generated method stub
		try {
			employeeRepository.delete(id);
			return "Employee with id " + id + " removed";
		} catch (Exception ex) {
			return "Employee with id " + id + " not found ";
		}
	}
}
