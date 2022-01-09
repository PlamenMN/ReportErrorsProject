package com.PNV.RepErrors.entities.payload.request;

public class ErrorTableRequest {
    private String errorCode;
    private String typeofError;
    private String description;
    private String username;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getErrorCode() {
        return errorCode;
    }

    public void setErrorCode(String errorCode) {
        this.errorCode = errorCode;
    }

    public String getTypeofError() {
        return typeofError;
    }

    public void setTypeofError(String typeofError) {
        this.typeofError = typeofError;
    }
}
