package com.oceanTech.L1.controller;

import com.oceanTech.L1.dto.request.CertificatePublicRequest;
import com.oceanTech.L1.util.ConverEmployeeDtoToEmployee;
import com.oceanTech.L1.dto.request.EmployeeCreateRequest;
import com.oceanTech.L1.entity.Employee;
import com.oceanTech.L1.exception.AppException;
import com.oceanTech.L1.exception.ErrorCode;
import com.oceanTech.L1.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("/employee")
public class EmployeeController {
    @Autowired
    private EmployeeService employeeService;

    @GetMapping("/myFirstApi")
    public String myFirstApi() {
        return "MyFirstApi";
    }

    @PostMapping("/addCertificate/{employeeId}")
    public Employee addCertificateToEmployee(@PathVariable Long employeeId, @RequestBody CertificatePublicRequest request) {
        return employeeService.addCertificateToEmployee(employeeId, request );
    }

    @PostMapping("/create")
    public Employee createEmployee(@RequestBody EmployeeCreateRequest request) {
        if(request.getProvince().isEmpty() || request.getDistrict().isEmpty() || request.getCommune().isEmpty()){
            Employee employee = new ConverEmployeeDtoToEmployee().convert(request);
            throw new AppException(ErrorCode.EMPLOYEE_LOCATION_REQUIRED, employee);
        }
        return employeeService.createEmployee(request);
    }

    @PutMapping("/updateEmployee/{id}")
    public Employee updateEmployee(@PathVariable String id, @RequestBody EmployeeCreateRequest request){
        if(request.getProvince().isEmpty() || request.getDistrict().isEmpty() || request.getCommune().isEmpty()){
            Employee employee = new ConverEmployeeDtoToEmployee().convert(request);
            throw new AppException(ErrorCode.EMPLOYEE_LOCATION_REQUIRED, employee);
        }
        return employeeService.updateEmployee(id, request);
    }

    @DeleteMapping("/deleteEmployee/{id}")
    String deleteEmployee(@PathVariable String id){
        employeeService.deleteEmployee(id);
        return "Employee deleted successfully";
    }

    @GetMapping("/getEmployee")
    List<Employee> getEmployee(){
        return employeeService.getEmployee();
    }

    @GetMapping("/getEmployeeByName/{name}")
    List<Employee> getEmployeeByName(@PathVariable String name){
        return employeeService.getEmployeeByName(name);
    }

    @GetMapping("/export")
    public ResponseEntity<String> saveEmployeesToFile() throws IOException {
        employeeService.saveEmployeesToExcel();
        return ResponseEntity.ok("File saved successfully to employees.xlsx");
    }

    @PutMapping("/import")
    public ResponseEntity<?> importEmployees(@RequestParam("file") MultipartFile file) {
        try {
            List<String> notFoundEmployees = employeeService.importEmployeesFromExcel(file);
            if (notFoundEmployees.isEmpty()) {
                return ResponseEntity.ok("Employees imported successfully");
            } else {
                return ResponseEntity.status(404).body(notFoundEmployees);
            }
        } catch (IOException e) {
            return ResponseEntity.status(500).body("Failed to import employees: " + e.getMessage());
        }
    }

}
