package com.backend.ecom.Exception;

public class AuthenticationFailException extends Exception {
    public AuthenticationFailException(String msg) {
        super(msg);
    }
}
