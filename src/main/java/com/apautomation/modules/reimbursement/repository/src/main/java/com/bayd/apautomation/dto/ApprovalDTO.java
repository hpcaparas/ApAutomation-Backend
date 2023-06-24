package com.bayd.apautomation.dto;

import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.Date;
import java.util.UUID;

@Data
@EqualsAndHashCode(callSuper = true)
public class ApprovalDTO extends AbstractDto {
    private UUID approvalid;
    private String status;
    private String approved_by;
    private String comments;
    private Date approved_on;
    private BillDTO approvalbill;
//    private List<Approval> approvals;

}
