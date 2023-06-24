package com.bayd.apautomation.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

import java.util.Date;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class AbstractDto {

    private Date createdOn;

    private String createdBy;

    private Date updatedOn;

    private String updatedBy;

    private Boolean isActive;

}
