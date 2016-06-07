package com.verizon.assignment;

import java.util.List;

import org.springframework.data.repository.CrudRepository;



public interface DepartmentRepository extends CrudRepository<Department, Long> {
    List<Department> findByName(String name);
    List<Department> findById(Long id);
}
