package com.oceanTech.L1.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;

public enum ErrorCode {
    EMPLOYEE_DUPLICATE_CODE(1001, "Code already exists", HttpStatus.BAD_REQUEST),
    EMPLOYEE_INVALID_CODE_FORMAT(1002, "Code must be between 6 and 10 characters and cannot contain spaces", HttpStatus.BAD_REQUEST),
    EMPLOYEE_NAME_REQUIRED(1003, "Name is mandatory", HttpStatus.BAD_REQUEST),
    EMPLOYEE_INVALID_EMAIL_FORMAT(1004, "Email should be valid", HttpStatus.BAD_REQUEST),
    EMPLOYEE_PHONE_REQUIRED(1005, "Phone is mandatory", HttpStatus.BAD_REQUEST),
    EMPLOYEE_INVALID_PHONE_FORMAT(1006, "Phone must be numeric and up to 11 digits", HttpStatus.BAD_REQUEST),
    EMPLOYEE_INVALID_AGE(1007, "Age cannot be negative", HttpStatus.BAD_REQUEST),
    EMPLOYEE_INVALID_DOB(1008, "Your age must be at least {min}", HttpStatus.BAD_REQUEST),
    EMPLOYEE_LOCATION_REQUIRED(1009, "Province, district and commune are mandatory", HttpStatus.BAD_REQUEST),
    EMPLOYEE_INVALID_COMMUNE(1010, "District must belong to the specified Province", HttpStatus.BAD_REQUEST),
    EMPLOYEE_INVALID_DISTRICT(1011, "District must belong to the specified Province", HttpStatus.BAD_REQUEST),
    DISTRICT_NOT_IN_PROVINCE(1012, "District must belong to the specified Province", HttpStatus.BAD_REQUEST),
    COMMUNE_NOT_IN_DISTRICT(1013, "Commune must belong to the specified District", HttpStatus.BAD_REQUEST),
    EMPLOYEE_PROVINCE_NOT_FOUND(1014, "Province not found", HttpStatus.BAD_REQUEST),
    EMPLOYEE_DISTRICT_NOT_FOUND(1015, "District not found", HttpStatus.BAD_REQUEST),
    EMPLOYEE_COMMUNE_NOT_FOUND(1016, "Commune not found", HttpStatus.BAD_REQUEST),
    PROVINCE_NOT_FOUND(1017, "Province not found", HttpStatus.BAD_REQUEST),
    CERTIFICATE_NOT_FOUND(1018, "Certificate not found", HttpStatus.BAD_REQUEST),
    EMPLOYEE_NOT_FOUND(1019, "Employee not found", HttpStatus.BAD_REQUEST),
    CERTIFICATE_ALREADY_EXISTS(1020,"certificate already exits" ,HttpStatus.BAD_REQUEST ),
    CERTIFICATE_LIMIT_EXCEEDED(1021,"certificate limit exceedded", HttpStatus.BAD_REQUEST),
    ;

    private final int code;
    private final String message;
    private final HttpStatusCode statusCode;

    ErrorCode(int code, String message, HttpStatusCode statusCode) {
        this.code = code;
        this.message = message;
        this.statusCode = statusCode;
    }

    public int getCode() {
        return code;
    }

    public String getMessage() {
        return message;
    }

    public HttpStatusCode getStatusCode() {
        return statusCode;
    }
}
