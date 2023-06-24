package com.bayd.apautomation.dto;

import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.List;
import java.util.UUID;
import java.util.Date;
@Data
@EqualsAndHashCode(callSuper = true)
public class BillDTO extends AbstractDto {
    private UUID billid;
    private String name;
    private Float amount;
    private Float tax;
    private String deptname;
    private String filename;
    private Date billdate;
    private UserDto billuser;
    private List<ApprovalDTO> approvals;

}
