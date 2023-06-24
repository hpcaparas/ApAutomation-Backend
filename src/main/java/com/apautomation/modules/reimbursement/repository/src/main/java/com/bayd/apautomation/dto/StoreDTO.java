package com.bayd.apautomation.dto;

import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.UUID;

@Data
@EqualsAndHashCode(callSuper = true)
public class StoreDTO extends AbstractDto {
    private UUID storeid;
    private String name;

}
