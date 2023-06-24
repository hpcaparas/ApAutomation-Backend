package com.bayd.apautomation.dto;

import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.UUID;

@Data
@EqualsAndHashCode(callSuper = true)
public class DepartmentDTO extends AbstractDto {
    private UUID deptid;
    private String name;

}
