package com.bayd.apautomation.controller;


import com.bayd.apautomation.dto.DepartmentDTO;
import com.bayd.apautomation.dto.DepartmentsDTO;
import com.bayd.apautomation.dto.ResponseDTO;
import com.bayd.apautomation.enums.Status;
import com.bayd.apautomation.service.DepartmentService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;
import java.util.UUID;

@RestController
@RequestMapping(value = "/department")
@RequiredArgsConstructor
public class DepartmentController implements AbstractController {

    private final DepartmentService departmentService;

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE, value = "/save")
    public ResponseEntity<ResponseDTO> save(@RequestBody DepartmentDTO DepartmentDTO, @RequestParam(name = "userId", required = false) UUID userUUID) {
        Optional<DepartmentDTO> save = Optional.empty();
        try {
            save = departmentService.save(DepartmentDTO, userUUID);
        } catch (Exception e) {
            return getResponseWithErrorData(Status.NOT_FOUND, e.getMessage());
        }
        return !save.isPresent() ? getResponse(Status.FAILED) : getResponseWithData(Status.SUCCESS, save.get());
    }

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE, value = "/{uuid}")
    public ResponseEntity<ResponseDTO> get(@PathVariable("uuid") UUID uuid) {
        Optional<DepartmentDTO> DepartmentDTO = departmentService.get(uuid);
        return !DepartmentDTO.isPresent() ? getResponse(Status.FAILED) : getResponseWithData(Status.SUCCESS, DepartmentDTO.get());
    }

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE, value = "/all")
    public ResponseEntity<ResponseDTO> getAllDepartments() {
        DepartmentsDTO departmentsDTO = departmentService.getAllDepartments();
        return departmentsDTO.getDepartmentsDTO().isEmpty() ? getResponse(Status.FAILED) : getResponseWithData(Status.SUCCESS, departmentsDTO);
    }

    @DeleteMapping(produces = MediaType.APPLICATION_JSON_VALUE, value = "/{uuid}")
    public ResponseEntity<ResponseDTO> delete(@PathVariable("uuid") UUID id) {
        return getResponse(departmentService.delete(id));
    }


}
