package com.midorlo.k9.exception;

public class K9UncheckedException extends RuntimeException {
    public K9UncheckedException() {
    }

    public K9UncheckedException(String message) {
        super(message);
    }
}
