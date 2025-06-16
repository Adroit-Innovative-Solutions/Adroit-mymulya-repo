package com.mymulya.globaltables.dto;


public class LoginResponse {
    private boolean success;
    private String message;
    private Payload payload;
    private String error;

    public LoginResponse(boolean success, String message, Payload payload, String error) {
        this.success = success;
        this.message = message;
        this.payload = payload;
        this.error = error;
    }

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Payload getPayload() {
        return payload;
    }

    public void setPayload(Payload payload) {
        this.payload = payload;
    }

    public String getError() {
        return error;
    }

    public void setError(String error) {
        this.error = error;
    }
// Getters and Setters
}
