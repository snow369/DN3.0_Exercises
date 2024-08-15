package com.example.EmployeeManagementSystem.repository;

import com.example.EmployeeManagementSystem.entity.Employee;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface EmployeeRepository extends JpaRepository<Employee, Long> {
    // Derived query methods
    List<Employee> findByDepartmentId(Long departmentId);
    List<Employee> findByNameContaining(String name);
}
