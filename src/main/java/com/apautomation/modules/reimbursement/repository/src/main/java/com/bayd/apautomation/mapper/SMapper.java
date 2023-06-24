package com.bayd.apautomation.mapper;

import java.util.List;

public interface SMapper<T, S> {
    T convertEntity(S s);

    S convertDto(T t);

    List<S> convertDtos(List<T> tList);

    List<T> convertEntities(List<S> sList);

}
