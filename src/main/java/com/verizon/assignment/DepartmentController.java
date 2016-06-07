package com.verizon.assignment;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class DepartmentController {

	@Autowired
	private DepartmentRepository departmentRepository;


    @RequestMapping("/department/query")
    public String greeting(@RequestParam(value="name") String name) {    	
		return departmentRepository.findByName(name).toString();
    }
    

    @RequestMapping("/department/save")
	public String save(@RequestParam(value="name") String departmentName, @RequestParam(value="min_salary_range")  double min, @RequestParam(value="max_salary_range") double max) {
		Department department = new Department(departmentName,min,max);
		departmentRepository.save(department);
		return "Department saved: "+ department.toString();
	}
    

    @RequestMapping(value="/department/queryWithId")
	public String findOne(@RequestParam(value="id") long id) {
		// TODO Auto-generated method stub
		Department findOne = departmentRepository.findOne(id);
		return findOne==null? "Not Found" : findOne.toString();
	}



    @RequestMapping(value="/department/delete")
	public String delete(@RequestParam(value="id") long id) {
		// TODO Auto-generated method stub
		departmentRepository.delete(id);
		return "Department with id "+ id + " removed";
	}
}
