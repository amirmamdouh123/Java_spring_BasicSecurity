package com.example.demo.errors.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_ACCEPTABLE)
public class BussinessException extends RuntimeException{
    private static final long serialVersionUID =1L;

    private Object[] objects=null;
    public BussinessException(String message){
        super(message);
    }
    public BussinessException(String message ,Throwable cause){
        super(message, cause);
    }

    public BussinessException(String message ,Object[] objects){
        super(message);
        this.objects=objects;
    }

    public Object[] getObjects() {
        return objects;
    }

    public void setObjects(Object[] objects) {
        this.objects = objects;
    }
}
