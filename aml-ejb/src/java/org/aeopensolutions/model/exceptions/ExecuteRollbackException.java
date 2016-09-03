package org.aeopensolutions.model.exceptions;

import javax.ejb.ApplicationException;

/**
 * 
 * @author JNK
 */ 
@ApplicationException(rollback = true)
public class ExecuteRollbackException extends Exception {

    public ExecuteRollbackException() {
        super();
    }

    public ExecuteRollbackException(Exception e) {
        super(e.getMessage(), e);
    }

    public ExecuteRollbackException(String message) {
        super(message);
    }
}
