package com.employeemanagement.errorhandlers;

import com.employeemanagement.exception.EmployeeNotFoundException;
import com.employeemanagement.exception.PayrollServiceException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import javax.servlet.http.HttpServletRequest;

@Slf4j
@RestControllerAdvice
public class SimpleControllerAdvice {

    @ExceptionHandler(EmployeeNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    String handleEmployeeNotFound(EmployeeNotFoundException exception, HttpServletRequest request){
        log.warn("Employee not found for request {} ",request.getRequestURI(),exception);
        return exception.getMessage();
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    String handleMethodArgumentNotValidException(MethodArgumentNotValidException exception, HttpServletRequest request){
        log.warn("Invalid argument passed for request {} ",request.getRequestURI(),exception);
        return exception.getLocalizedMessage();
    }

    @ExceptionHandler(IllegalArgumentException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    String handleIllegalArgumentException(IllegalArgumentException exception, HttpServletRequest request){
        log.warn("Rest api failed for request {} ",request.getRequestURI(),exception);
        return exception.getMessage();
    }

    @ExceptionHandler({PayrollServiceException.class,})
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    String handleException(PayrollServiceException exception, HttpServletRequest request){
        log.error("Payroll service failed for request {} ",request.getRequestURI(),exception);
        return exception.getMessage();
    }

    @ExceptionHandler({Exception.class})
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    String handleException(Exception exception, HttpServletRequest request){
        log.error("Rest api failed for request {} ",request.getRequestURI(),exception);
        return exception.getMessage();
    }


}
