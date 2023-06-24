package com.bayd.apautomation.service;

import com.bayd.apautomation.dto.BillDTO;
import com.bayd.apautomation.dto.BillReqDTO;
import com.bayd.apautomation.dto.DepartmentDTO;
import com.bayd.apautomation.dto.DepartmentsDTO;

import java.io.IOException;
import java.util.Optional;
import java.util.UUID;

public interface BillService extends AbstractService<BillDTO, UUID> {

    BillDTO getAllBills();

    BillDTO saveorupdate(BillReqDTO billReqDTO, UUID userUUID) throws IOException;
}
