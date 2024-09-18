package com.oceanTech.L1.service;

import com.oceanTech.L1.dto.request.CertificatePublicRequest;
import com.oceanTech.L1.dto.request.EmployeeCreateRequest;
import com.oceanTech.L1.entity.*;
import com.oceanTech.L1.exception.AppException;
import com.oceanTech.L1.exception.ErrorCode;
import com.oceanTech.L1.repository.*;
import com.oceanTech.L1.util.ExcelHelper;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class EmployeeService {
    @Autowired
    private EmployeeRepository employeeRepository;

    @Autowired
    private ProvinceRepository provinceRepository;

    @Autowired
    private CertificateRepository certificateRepository;

    @Autowired
    private DistrictRepository districtRepository;

    @Autowired
    private CommuneRepository communeRepository;

    public Employee addCertificateToEmployee(Long employeeId, CertificatePublicRequest request) {
        Employee employee = employeeRepository.findById(employeeId);
        if (employee == null) {
            throw new AppException(ErrorCode.EMPLOYEE_NOT_FOUND);
        }

        Certificate certificate = certificateRepository.findCustomId(request.getIdCertificate());
        if (certificate == null) {
            throw new AppException(ErrorCode.CERTIFICATE_NOT_FOUND);
        }

        Province province = provinceRepository.findByName(request.getProvinceName());
        if (province == null) {
            throw new AppException(ErrorCode.PROVINCE_NOT_FOUND);
        }

        List<Certificate> existingCertificates = employee.getCertificates();

        // Check if the employee already has the same certificate from the same province
        for (Certificate cert : existingCertificates) {
            if (cert.getName().equals(certificate.getName()) && cert.getProvince().equals(certificate.getProvince()) && cert.getValidTo().isAfter(LocalDate.now())) {
                throw new AppException(ErrorCode.CERTIFICATE_ALREADY_EXISTS);
            }
        }

        // Check if the employee has more than 3 valid certificates of the same type
        long validCertificatesCount = existingCertificates.stream()
                .filter(cert -> cert.getName().equals(certificate.getName()) && cert.getValidTo().isAfter(LocalDate.now()))
                .count();

        if (validCertificatesCount >= 3) {
            throw new AppException(ErrorCode.CERTIFICATE_LIMIT_EXCEEDED);
        }

        certificate.setValidFrom(request.getValidFrom());
        certificate.setValidTo(request.getValidTo());
        certificate.setProvince(province.getName());
        certificate.setEmployee(employee);

        certificateRepository.save(certificate);

        return employee;
    }

    public Employee createEmployee(EmployeeCreateRequest request){
        Employee employee = new Employee();

        employee.setCode(request.getCode());
        employee.setName(request.getName());
        employee.setEmail(request.getEmail());
        employee.setPhone(request.getPhone());
        employee.setAge(request.getAge());

        Province province = new Province();
        province.setName(request.getProvince());
        employee.setProvince(province);

        District district = new District();
        district.setName(request.getDistrict());
        district.setProvince(province);
        employee.setDistrict(district);

        Commune commune = new Commune();
        commune.setName(request.getCommune());
        commune.setDistrict(district);
        employee.setCommune(commune);

        return employeeRepository.save(employee);
    }

    public Employee updateEmployee(String idEmployee, EmployeeCreateRequest request) {
        // Find the employee by ID
        Employee employee = employeeRepository.findById(Long.parseLong(idEmployee));

        Province province = new Province();
        province.setName(request.getProvince());
        employee.setProvince(province);

        District district = new District();
        district.setName(request.getDistrict());
        district.setProvince(province);
        employee.setDistrict(district);

        Commune commune = new Commune();
        commune.setName(request.getCommune());
        commune.setDistrict(district);
        employee.setCommune(commune);

        employee.setProvince(province);
        employee.setDistrict(district);
        employee.setCommune(commune);

        employee.setCode(request.getCode());
        employee.setName(request.getName());
        employee.setEmail(request.getEmail());
        employee.setPhone(request.getPhone());
        employee.setAge(request.getAge());

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
//            file.getParentFile().mkdirs();
            try (FileOutputStream fileOut = new FileOutputStream(file)) {
                workbook.write(fileOut);
            }
        }
    }

    public List<String> importEmployeesFromExcel(MultipartFile file) throws IOException {
        List<EmployeeCreateRequest> employees = ExcelHelper.excelToEmployees(file);
        List<String> notFoundEmployees = new ArrayList<>();
        int lineNumber = 1; // Start from 1 to account for header row

        for (EmployeeCreateRequest request : employees) {
            lineNumber++;
            Optional<Employee> employeeOpt = employeeRepository.findById(request.getCode());
            if (employeeOpt.isEmpty()) {
                notFoundEmployees.add("Line " + lineNumber + ": Employee ID " + request.getCode() + " not found");
            } else {
                Employee employee = employeeOpt.get();

                // Create and set province, district, and commune
                Province province = new Province();
                province.setName(request.getProvince());
                provinceRepository.save(province);

                District district = new District();
                district.setName(request.getDistrict());
                district.setProvince(province);
                districtRepository.save(district);

                Commune commune = new Commune();
                commune.setName(request.getCommune());
                commune.setDistrict(district);
                communeRepository.save(commune);

                // Set the location data to the employee
                employee.setProvince(province);
                employee.setDistrict(district);
                employee.setCommune(commune);
                employeeRepository.save(employee);
            }
        }

        return notFoundEmployees;
    }


}