package com.bayd.apautomation.dto;

import lombok.Data;

import java.util.List;

@Data
public class StoresDTO {
    private int page;
    private int limit;
    private long total;
    private List<StoreDTO> storeDTOS;
}
