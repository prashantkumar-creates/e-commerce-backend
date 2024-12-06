package com.backend.ecom.Exception;

public class CustomException extends IllegalArgumentException{

    public   CustomException  (String message){
        super(message);
    }
}
