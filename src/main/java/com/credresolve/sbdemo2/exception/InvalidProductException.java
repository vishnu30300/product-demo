package com.credresolve.sbdemo2.exception;

public class InvalidProductException extends RuntimeException{
    public InvalidProductException(String message){
        super(message);
    }
}
