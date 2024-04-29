package org.fdschinecenter.exceptions;

/**
 * Class to manage UserNotFoundException.
 */
public class UserNotFoundException extends RuntimeException{

    public UserNotFoundException(String message){
        super(message);
    }
}
