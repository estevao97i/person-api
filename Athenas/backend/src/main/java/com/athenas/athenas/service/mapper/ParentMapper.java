package com.athenas.athenas.service.mapper;

import java.util.List;

public interface ParentMapper<D, E> {

    E toEntity(D dto);

    D toDto(E entity);

    List<E> toEntity(List<D> dtoList);

    List<D> toDto(List<E> entityList);
}
