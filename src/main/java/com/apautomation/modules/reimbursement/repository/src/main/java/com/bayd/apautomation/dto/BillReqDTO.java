package com.bayd.apautomation.dto;

import lombok.Data;
import lombok.EqualsAndHashCode;
import org.springframework.web.multipart.MultipartFile;

import java.sql.Date;
import java.util.List;
import java.util.UUID;

@Data
@EqualsAndHashCode(callSuper = true)
public class BillReqDTO extends AbstractDto {
    private UUID billid;
    private String name;
    private Float amount;
    private Float tax;
    private String deptname;
    private String filename;
    private Date billdate;
    private UUID userid;
    private MultipartFile billfile;
    private List<ApprovalDTO> approvals;

}
