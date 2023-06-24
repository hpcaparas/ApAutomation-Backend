package com.bayd.apautomation.controller;

import com.bayd.apautomation.dto.LoginDTO;
import com.bayd.apautomation.dto.ResponseDTO;
import com.bayd.apautomation.dto.StoreDTO;
import com.bayd.apautomation.dto.UserDto;
import com.bayd.apautomation.enums.Status;
import com.bayd.apautomation.service.impl.UserServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Objects;
import java.util.Optional;
import java.util.UUID;

@RestController
@RequiredArgsConstructor
public class LoginController implements AbstractController {

    private final UserServiceImpl userService;

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE, value = "login")
    public ResponseEntity<ResponseDTO> userLogin(@RequestBody LoginDTO loginDTO) {
        ResponseDTO response = new ResponseDTO();
        response.setStatus(Status.SUCCESS);
        response.setData(loginDTO);
        return ResponseEntity.ok(response);
    }


    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE, value = "/signup")
    public ResponseEntity<ResponseDTO> userRegister(@RequestBody UserDto userDto) {
        Optional<UserDto> save = Optional.empty();
        try {
            save = userService.saved(userDto);
        } catch (Exception e) {
            return getResponseWithErrorData(Status.NOT_FOUND, e.getMessage());
        }

        return !save.isPresent() ? getResponse(Status.FAILED) : getResponseWithData(Status.SUCCESS, save.get());
    }

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE, value = "logins")
    public ResponseEntity<ResponseDTO> getUserLogin() {
        ResponseDTO response = new ResponseDTO();
        return ResponseEntity.ok(response);
    }

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE, value = "/{uuid}")
    public ResponseEntity<ResponseDTO> get(@PathVariable("uuid") UUID uuid) {
        UserDto userDto = userService.getById(uuid);
        return Objects.isNull(userDto) ? getResponse(Status.FAILED) : getResponseWithData(Status.SUCCESS, userDto);
    }

}
