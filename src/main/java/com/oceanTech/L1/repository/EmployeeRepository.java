package com.oceanTech.L1.repository;

import com.oceanTech.L1.entity.Employee;
import com.oceanTech.L1.entity.Province;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Repository
public interface EmployeeRepository extends JpaRepository<Employee, String> {
    List<Employee> findByName(String name);
//    Optional<Employee> findById(Long id);
    Employee findById(Long id);
}
