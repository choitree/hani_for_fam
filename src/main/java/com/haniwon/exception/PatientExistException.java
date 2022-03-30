package com.haniwon.exception;

import javax.persistence.EntityExistsException;

public class PatientExistException extends RuntimeException {

    public PatientExistException() {
        super();
    }

    public PatientExistException(String message) {
        super(message);
    }
}
