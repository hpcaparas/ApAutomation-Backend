package com.bayd.apautomation.dto;

import com.bayd.apautomation.enums.Status;
import lombok.Getter;
import lombok.Setter;


public class ResponseDTO {
    private String message;
    private Object data;
    private Status status;
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	public Object getData() {
		return data;
	}
	public void setData(Object data) {
		this.data = data;
	}
	public Status getStatus() {
		return status;
	}
	public void setStatus(Status status) {
		this.status = status;
	}
}
