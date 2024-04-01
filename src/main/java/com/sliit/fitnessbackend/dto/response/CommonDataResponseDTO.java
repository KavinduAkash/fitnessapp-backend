package com.sliit.fitnessbackend.dto.response;

public class CommonDataResponseDTO<T> {
    private boolean success;
    private String message;
    private T body;

    public CommonDataResponseDTO() {
    }

    public CommonDataResponseDTO(boolean success, String message, T body) {
        this.success = success;
        this.message = message;
        this.body = body;
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

    public T getBody() {
        return body;
    }

    public void setBody(T body) {
        this.body = body;
    }

    @Override
    public String toString() {
        return "CommonDataResponseDTO{" +
                "success=" + success +
                ", message='" + message + '\'' +
                ", body=" + body +
                '}';
    }
}
