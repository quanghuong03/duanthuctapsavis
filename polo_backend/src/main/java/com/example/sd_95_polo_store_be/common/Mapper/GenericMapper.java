package com.example.sd_95_polo_store_be.common.Mapper;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;
import java.util.List;

@Mapper
public interface GenericMapper<R,E> {
    GenericMapper INSTANCE = Mappers.getMapper(GenericMapper.class);

    R toDto(E entity);

    List<R> toDtoList(List<E> entityList);

    E toEntity(R target);

    List<E> toEntityList(List<R> dtoList);
}
