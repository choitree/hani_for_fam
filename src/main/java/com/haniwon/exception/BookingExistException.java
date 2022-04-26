package com.haniwon.exception;

public class BookingExistException extends RuntimeException{

    public BookingExistException() {
        super();
    }

    public BookingExistException(String message) {
        super(message);
    }
}
