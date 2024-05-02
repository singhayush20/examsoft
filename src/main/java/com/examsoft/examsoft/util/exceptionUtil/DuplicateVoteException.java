package com.examsoft.examsoft.util.exceptionUtil;

public class DuplicateVoteException extends RuntimeException {

    public DuplicateVoteException (String message) {
        super(message);
    }
}
