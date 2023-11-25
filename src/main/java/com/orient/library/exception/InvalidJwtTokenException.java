package com.orient.library.exception;

public class InvalidJwtTokenException extends RuntimeException{
    InvalidJwtTokenException(String message){
        super(message);
    }
}
