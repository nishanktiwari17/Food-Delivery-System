package org.fdschinecenter.exceptions;

/**
Custom exception for user already exists.
*/

public class UserAlreadyExistsException extends RuntimeException {
    public UserAlreadyExistsException(String message) {
        super(message);
    }
}