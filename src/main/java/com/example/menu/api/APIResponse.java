package com.example.menu.api;

import lombok.Data;
import lombok.ToString;

@Data
@ToString
public class APIResponse {
    private int status;
    private String message;
    private Object data;

    public void setStatus(int status) {
        this.status = status;
    }
    public void setMessage(String message) {
        this.message = message;
    }
    public void setData(Object data) {
        this.data = data;
    }
}
