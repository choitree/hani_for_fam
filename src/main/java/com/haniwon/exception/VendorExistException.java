package com.haniwon.exception;

public class VendorExistException extends RuntimeException {

        public VendorExistException() {
            super();
        }

        public VendorExistException(String message) {
            super(message);
        }

}
