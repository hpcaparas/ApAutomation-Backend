package com.bayd.apautomation.exception;

public class CustomResourceNotFoundException extends RuntimeException {
    public CustomResourceNotFoundException(String uuid) {
        super(String.valueOf(uuid));
    }
}
