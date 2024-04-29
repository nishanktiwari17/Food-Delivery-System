package org.fdschinecenter.exceptions;

/**
    * Custom exception for password mismatch.
 */
public class PasswordMismatchException extends RuntimeException{
    public PasswordMismatchException(String message){
        super(message);
    }

}

