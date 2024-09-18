package com.oceanTech.L1.util;

import com.oceanTech.L1.dto.request.EmployeeCreateRequest;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class ExcelHelper {

    public static List<EmployeeCreateRequest> excelToEmployees(MultipartFile file) throws IOException {
        List<EmployeeCreateRequest> employees = new ArrayList<>();
        Workbook workbook = new XSSFWorkbook(file.getInputStream());
        Sheet sheet = workbook.getSheetAt(0);
        for (Row row : sheet) {
            if (row.getRowNum() == 0) continue; // Skip header row
            EmployeeCreateRequest employee = new EmployeeCreateRequest();
            employee.setCode(getCellValue(row.getCell(0)));
            employee.setProvince(getCellValue(row.getCell(1)));
            employee.setDistrict(getCellValue(row.getCell(2)));
            employee.setCommune(getCellValue(row.getCell(3)));
            employees.add(employee);
        }
        workbook.close();
        return employees;
    }

    private static String getCellValue(Cell cell) {
        if (cell == null) {
            return "";
        }
        switch (cell.getCellType()) {
            case STRING:
                return cell.getStringCellValue();
            case NUMERIC:
                return String.valueOf((int) cell.getNumericCellValue());
            case BOOLEAN:
                return String.valueOf(cell.getBooleanCellValue());
            case FORMULA:
                return cell.getCellFormula();
            default:
                return "";
        }
    }
}