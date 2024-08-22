package com.oceanTech.L1.controller;

import com.oceanTech.L1.dto.request.EmployeeSearchRequest;
import com.oceanTech.L1.entity.Employee;
import com.oceanTech.L1.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("/employee")
public class EmployeeController {
    @Autowired
    private EmployeeService employeeService;

    @PostMapping("/create")
    Employee createEmployee(@RequestBody Employee request) {
        return employeeService.createEmployee(request);
    }

    @GetMapping("/getEmployee")
    List<Employee> getEmployee(){
        return employeeService.getEmployee();
    }

    @GetMapping("/getEmployeeByName/{name}")
    List<Employee> getEmployeeByName(@PathVariable String name){
        return employeeService.getEmployeeByName(name);
    }

    @DeleteMapping("/deleteEmployee/{id}")
    String deleteEmployee(@PathVariable String id){
        employeeService.deleteEmployee(id);
        return "Employee deleted successfully";
    }


    @GetMapping("/export")
    public ResponseEntity<String> saveEmployeesToFile() throws IOException {
        employeeService.saveEmployeesToExcel();
        return ResponseEntity.ok("File saved successfully to employees.xlsx");
    }

}
