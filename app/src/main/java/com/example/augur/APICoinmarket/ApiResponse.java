package com.example.augur.APICoinmarket;

public class ApiResponse<T> {
    boolean success;
    T data;

    public ApiResponse() {
    }

    public ApiResponse(boolean success, T data) {
        this.success = success;
        this.data = data;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public boolean getSuccess() {
        return this.success;
    }

    public void setData(T data) {
        this.data = data;
    }

    public T getData() {
        return this.data;
    }
}
