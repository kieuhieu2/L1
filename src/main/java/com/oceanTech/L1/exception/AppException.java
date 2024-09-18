package com.oceanTech.L1.exception;

import com.oceanTech.L1.entity.Employee;

public class AppException extends RuntimeException{
    private ErrorCode errorCode;
    private Employee data;

    public AppException(ErrorCode errorCode, Employee data) {
        super(errorCode.getMessage());
        this.errorCode = errorCode;
        this.data = data;
    }

    public AppException(ErrorCode errorCode) {
        super(errorCode.getMessage());
        this.errorCode = errorCode;
    }

    public AppException(ErrorCode errorCode, String message) {
        super(message);
        this.errorCode = errorCode;
    }

    public ErrorCode getErrorCode() {
        return errorCode;
    }

    public void setErrorCode(ErrorCode errorCode) {
        this.errorCode = errorCode;
    }

    public Employee getData() {
        return data;
    }
}
