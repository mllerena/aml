package org.aeopensolutions.model.exceptions;

/**
 *
 * @author JNK
 */ 
public class ExistException extends Exception {
    
    public ExistException() {
        super();
    }
    
    public ExistException(ExistException e) {
        super(e.getMessage(), e);
    }
}
