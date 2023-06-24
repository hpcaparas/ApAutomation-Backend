package com.bayd.apautomation.service;


import com.bayd.apautomation.enums.Status;

import java.util.Optional;
import java.util.UUID;

public interface AbstractService<T, ID> {
    Optional<T> save(T businessDto, UUID userUUID);

    Optional<T> get(ID uuid);

    Status delete(ID uuid);
}
