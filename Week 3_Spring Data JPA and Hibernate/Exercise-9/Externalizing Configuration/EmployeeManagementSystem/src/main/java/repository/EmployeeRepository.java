package com.example.EmployeeManagementSystem.repository;

import com.example.EmployeeManagementSystem.entity.Employee;
import com.example.EmployeeManagementSystem.entity.EmployeeProjection;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import java.util.List;

@Repository
public interface EmployeeRepository extends JpaRepository<Employee, Long> {
    // No need to add anything here; JpaRepository already provides methods for pagination and sorting.
    @Query("SELECT e.name as name, e.email as email, d.name as departmentName " +
            "FROM Employee e JOIN e.department d WHERE e.id = :id")
    EmployeeProjection findEmployeeById(@Param("id") Long id);

    @Query("SELECT e.name as name, e.email as email, d.name as departmentName " +
            "FROM Employee e JOIN e.department d")
    List<EmployeeProjection> findAllProjectedEmployees();
}

