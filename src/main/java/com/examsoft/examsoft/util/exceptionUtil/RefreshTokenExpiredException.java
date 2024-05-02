package com.examsoft.examsoft.util.exceptionUtil;

public class RefreshTokenExpiredException extends RuntimeException {

    public RefreshTokenExpiredException(String s) {
        super(s);
    }
}
