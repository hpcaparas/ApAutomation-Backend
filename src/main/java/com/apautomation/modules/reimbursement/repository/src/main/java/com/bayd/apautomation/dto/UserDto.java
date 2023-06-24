package com.bayd.apautomation.dto;

import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.List;
import java.util.Set;
import java.util.UUID;

@Data
@EqualsAndHashCode(callSuper = true)
public class UserDto extends AbstractDto {
    private UUID userid;
    private String firstname;
    private String lastname;
    private String password;
    private String email;
    private String mobile;
    private Boolean isverified;
    private List<DepartmentDTO> userdepartments;

}
