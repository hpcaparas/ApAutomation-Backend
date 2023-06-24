package com.bayd.apautomation.service;

import com.bayd.apautomation.dto.StoreDTO;
import com.bayd.apautomation.dto.StoresDTO;

import java.util.UUID;

public interface StoreService extends AbstractService<StoreDTO, UUID> {

    StoresDTO getAllStores();
}
