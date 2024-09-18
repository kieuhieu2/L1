package com.oceanTech.L1.validator;

import com.oceanTech.L1.entity.Employee;
import com.oceanTech.L1.exception.AppException;
import com.oceanTech.L1.exception.ErrorCode;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class EmployeeValidator implements ConstraintValidator<EmployeeConstraint, Employee> {

    @Override
    public boolean isValid(Employee employee, ConstraintValidatorContext context) {
        if (employee.getCode() == null || !employee.getCode().matches("[A-Z0-9]{6,10}")) {
            throw new AppException(ErrorCode.EMPLOYEE_INVALID_CODE_FORMAT, employee);
        }
        if (employee.getName() == null || employee.getName().isEmpty()) {
            throw new AppException(ErrorCode.EMPLOYEE_NAME_REQUIRED, employee);
        }
        if (employee.getEmail() == null || !employee.getEmail().matches("^[A-Za-z0-9+_.-]+@(.+)$")) {
            throw new AppException(ErrorCode.EMPLOYEE_INVALID_EMAIL_FORMAT, employee);
        }
        if (employee.getPhone() == null || !employee.getPhone().matches("\\d{1,11}")) {
            throw new AppException(ErrorCode.EMPLOYEE_INVALID_PHONE_FORMAT, employee);
        }
        if (employee.getAge() < 0) {
            throw new AppException(ErrorCode.EMPLOYEE_INVALID_AGE, employee);
        }
        return true;
    }

}