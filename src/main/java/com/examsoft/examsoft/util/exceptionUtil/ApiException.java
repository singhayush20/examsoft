package com.examsoft.examsoft.util.exceptionUtil;

public class ApiException extends RuntimeException{
    
    public ApiException(String message){
        super(message);
    }
}
