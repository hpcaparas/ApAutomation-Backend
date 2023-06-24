package com.bayd.apautomation.controller;

import com.bayd.apautomation.dto.ResponseDTO;
import com.bayd.apautomation.enums.Status;
import org.springframework.http.ResponseEntity;

public interface AbstractController {
    default ResponseEntity<ResponseDTO> getResponse(Status status) {
        ResponseDTO response = new ResponseDTO();
        response.setStatus(status);
        return ResponseEntity.ok(response);
    }

    default ResponseEntity<ResponseDTO> getResponseWithData(Status status, Object o) {
        ResponseDTO response = new ResponseDTO();
        response.setStatus(status);
        response.setData(o);
        return ResponseEntity.ok(response);
    }

    default ResponseEntity<ResponseDTO> getResponseWithErrorData(Status status, String o) {
        ResponseDTO response = new ResponseDTO();
        response.setStatus(status);
        response.setMessage(o);
        return ResponseEntity.ok(response);
    }


}
