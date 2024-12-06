package com.backend.ecom.dto;

public class ResponeDto
{
    private String status;
    private String message;

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public ResponeDto(String status, String message) {
        this.status = status;
        this.message = message;
    }
}
