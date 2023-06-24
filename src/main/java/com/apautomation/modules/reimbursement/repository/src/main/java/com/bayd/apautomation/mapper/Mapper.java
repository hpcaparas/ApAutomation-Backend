package com.bayd.apautomation.mapper;


import com.bayd.apautomation.entity.AbstractEntity;

import java.util.Date;

public interface Mapper<T extends AbstractEntity, S> extends SMapper<T, S> {
    default void prepareForUpdate(T t) {
        t.setUpdatedon(new Date());
    }

    default void prepareForCreate(T t) {
        t.setCreatedon(new Date());
        t.setIsactive(true);
        t.setIsdeleted(false);
    }

    default void prepareForDelete(T t) {
        t.setIsdeleted(true);
        t.setIsactive(false);
        prepareForUpdate(t);

    }
}
