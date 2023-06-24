package com.bayd.apautomation.enums;

public enum Status {
    SUCCESS(200, "Success"), NOT_FOUND(401, "Not Found"), FAILED(500, "Internal Server Error.!"), NOT_MODIFIED(304, "Not Modified");
    private int code;
    private String status;

    Status(int code, String status) {
        this.code = code;
        this.status = status;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
