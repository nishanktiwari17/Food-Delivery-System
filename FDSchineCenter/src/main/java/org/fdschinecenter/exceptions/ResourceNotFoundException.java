package org.fdschinecenter.exceptions;
/**
 * Class to manage ResourceNotFoundException.
 */
public class ResourceNotFoundException extends RuntimeException{

    public ResourceNotFoundException(String message){
        super(message);
    }
}
