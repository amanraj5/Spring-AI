package com.springai.monitoring.dto;

public class AIResponse {
    private String response;
    private Boolean success;

    public AIResponse(String response, Boolean success) {
        this.response = response;
        this.success = success;
    }

    public AIResponse() {
    }

    public String getResponse() {
        return response;
    }

    public void setResponse(String response) {
        this.response = response;
    }

    public Boolean getSuccess() {
        return success;
    }

    public void setSuccess(Boolean success) {
        this.success = success;
    }
}
