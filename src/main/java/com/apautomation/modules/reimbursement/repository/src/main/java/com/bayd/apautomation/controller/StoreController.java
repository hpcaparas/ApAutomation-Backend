package com.bayd.apautomation.controller;


import com.bayd.apautomation.dto.StoreDTO;
import com.bayd.apautomation.dto.DepartmentsDTO;
import com.bayd.apautomation.dto.ResponseDTO;
import com.bayd.apautomation.dto.StoresDTO;
import com.bayd.apautomation.enums.Status;
import com.bayd.apautomation.service.DepartmentService;
import com.bayd.apautomation.service.StoreService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;
import java.util.UUID;

@RestController
@RequestMapping(value = "/store")
@RequiredArgsConstructor
public class StoreController implements AbstractController {

    private final StoreService storeService;

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE, value = "/save")
    public ResponseEntity<ResponseDTO> save(@RequestBody StoreDTO StoreDTO, @RequestParam(name = "userId", required = false) UUID userUUID) {
        Optional<StoreDTO> save = Optional.empty();
        try {
            save = storeService.save(StoreDTO, userUUID);
        } catch (Exception e) {
            return getResponseWithErrorData(Status.NOT_FOUND, e.getMessage());
        }
        return !save.isPresent() ? getResponse(Status.FAILED) : getResponseWithData(Status.SUCCESS, save.get());
    }

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE, value = "/{uuid}")
    public ResponseEntity<ResponseDTO> get(@PathVariable("uuid") UUID uuid) {
        Optional<StoreDTO> StoreDTO = storeService.get(uuid);
        return !StoreDTO.isPresent() ? getResponse(Status.FAILED) : getResponseWithData(Status.SUCCESS, StoreDTO.get());
    }

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE, value = "/all")
    public ResponseEntity<ResponseDTO> getAllDepartments() {
        StoresDTO storesDTO = storeService.getAllStores();
        return storesDTO.getStoreDTOS().isEmpty() ? getResponse(Status.FAILED) : getResponseWithData(Status.SUCCESS, storesDTO);
    }

    @DeleteMapping(produces = MediaType.APPLICATION_JSON_VALUE, value = "/{uuid}")
    public ResponseEntity<ResponseDTO> delete(@PathVariable("uuid") UUID id) {
        return getResponse(storeService.delete(id));
    }


}
