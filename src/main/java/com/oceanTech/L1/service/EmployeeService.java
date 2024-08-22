package com.oceanTech.L1.service;

import com.oceanTech.L1.dto.request.EmployeeSearchRequest;
import com.oceanTech.L1.entity.Employee;
import com.oceanTech.L1.repository.EmployeeRepository;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.*;
import java.util.List;
import java.util.Optional;

@Service
public class EmployeeService {
    @Autowired
    private EmployeeRepository employeeRepository;

    public Employee createEmployee(Employee request){
        Employee employee = new Employee();

        employee.setId(request.getId());
        employee.setCode(request.getCode());
        employee.setName(request.getName());
        employee.setEmail(request.getEmail());
        employee.setEmail(request.getEmail());
        employee.setPhone(request.getPhone());

        return employeeRepository.save(employee);
    }

    public List<Employee> getEmployee(){
        return employeeRepository.findAll();
    }

    public List<Employee> getEmployeeByName(String name){
        return employeeRepository.findByName(name);
    }

    public void deleteEmployee(String id){
        employeeRepository.deleteById(id);
    }

    public void saveEmployeesToExcel() throws IOException {
        String[] columns = {"STT", "Tên", "Mã", "Email", "Phone", "Age"};
        List<Employee> employees = employeeRepository.findAll();

        try (Workbook workbook = new XSSFWorkbook()) {
            Sheet sheet = workbook.createSheet("Employees");

            // Create header row
            Row headerRow = sheet.createRow(0);
            for (int i = 0; i < columns.length; i++) {
                Cell cell = headerRow.createCell(i);
                cell.setCellValue(columns[i]);
            }

            // Create data rows
            int rowIdx = 1;
            for (Employee employee : employees) {
                Row row = sheet.createRow(rowIdx++);

                row.createCell(0).setCellValue(rowIdx - 1);
                row.createCell(1).setCellValue(employee.getName());
                row.createCell(2).setCellValue(employee.getCode());
                row.createCell(3).setCellValue(employee.getEmail());
                row.createCell(4).setCellValue(employee.getPhone());
                row.createCell(5).setCellValue(employee.getAge());
            }

            // Save the file to the data folder
            File file = new File("/home/kieuhieu2/Desktop/20240823_Kiều_Quốc_Hiếu_L1/employees.xlsx");
//            file.getParentFile().mkdirs(); // Create directories if they do not exist
            try (FileOutputStream fileOut = new FileOutputStream(file)) {
                workbook.write(fileOut);
            }
        }
    }

}