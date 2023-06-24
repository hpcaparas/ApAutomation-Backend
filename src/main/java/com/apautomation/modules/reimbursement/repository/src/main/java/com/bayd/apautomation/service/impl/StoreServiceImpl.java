package com.bayd.apautomation.service.impl;

import com.bayd.apautomation.dto.StoreDTO;
import com.bayd.apautomation.dto.StoresDTO;
import com.bayd.apautomation.entity.Store;
import com.bayd.apautomation.enums.Status;
import com.bayd.apautomation.exception.CustomResourceNotFoundException;
import com.bayd.apautomation.mapper.StoreMapper;
import com.bayd.apautomation.repository.StoreRepo;
import com.bayd.apautomation.service.StoreService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class StoreServiceImpl implements StoreService {

    private final StoreRepo storeRepo;
    private final StoreMapper storeMapper = StoreMapper.INSTANCE;

    @Override
    public Optional<StoreDTO> save(StoreDTO businessDto, UUID userUUID) {

        if (Objects.isNull(businessDto) || Objects.isNull(businessDto.getName())) {
            throw new CustomResourceNotFoundException("Please enter valid Data");
        }
        Store store = storeMapper.convertEntity(businessDto);

        Optional<Store> byName = storeRepo.findByName(businessDto.getName());
        if (byName.isPresent()) {
            throw new CustomResourceNotFoundException("Store already exists");
        }

        if (Objects.nonNull(businessDto.getStoreid())) {
            Optional<Store> byId = storeRepo.findById(businessDto.getStoreid());
            if (byId.isPresent()) {
                store.setStoreid(byId.get().getStoreid());
                storeMapper.prepareForUpdate(store);
            }
        } else {
            store.setStoreid(UUID.randomUUID());
            storeMapper.prepareForCreate(store);
        }

        Store save = storeRepo.save(store);
        return Optional.ofNullable(storeMapper.convertDto(save));
    }

    @Override
    public Optional<StoreDTO> get(UUID uuid) {
        Optional<Store> byId = storeRepo.findById(uuid);
        return byId.map(storeMapper::convertDto);
    }

    @Override
    public Status delete(UUID uuid) {
        Optional<Store> byId = storeRepo.findById(uuid);
        if (byId.isPresent()) {
            byId.get().setIsdeleted(Boolean.TRUE);
            storeRepo.save(byId.get());
            return Status.SUCCESS;
        }
        return Status.FAILED;

    }

    @Override
    public StoresDTO getAllStores() {
        List<Store> all = storeRepo.findAll();
        StoresDTO storesDTO = new StoresDTO();
        storesDTO.setStoreDTOS(storeMapper.convertDtos(all));
        return storesDTO;
    }
}
