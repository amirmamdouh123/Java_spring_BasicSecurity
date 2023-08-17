package com.example.demo.errors;

import com.example.demo.errors.exceptions.BussinessException;
import com.example.demo.errors.exceptions.DataNotFoundException;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.util.Date;

@ControllerAdvice
public class GeneralExceptionHandler extends ResponseEntityExceptionHandler {

    private static final org.slf4j.Logger LOGGER = LoggerFactory.getLogger(GeneralExceptionHandler.class);


    @ExceptionHandler(DataNotFoundException.class)
    public final ResponseEntity<ErrorDto> handleDataNotFoundException(DataNotFoundException ex,
                                                                      WebRequest request){
        LOGGER.warn(ex.getMessage());

        ErrorDto errorDetails =new ErrorDto(new Date(),ex.getMessage(),request.getDescription(false));

        return new ResponseEntity<>(errorDetails, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(BussinessException.class)
    public final ResponseEntity<ErrorDto> handleBussinessException(BussinessException ex ,WebRequest request){
        LOGGER.warn(ex.getMessage());
        ErrorDto errorDto =new ErrorDto(new Date(),ex.getMessage(),request.getDescription(false));
        return new ResponseEntity<>(errorDto,HttpStatus.INTERNAL_SERVER_ERROR);
    }

}
