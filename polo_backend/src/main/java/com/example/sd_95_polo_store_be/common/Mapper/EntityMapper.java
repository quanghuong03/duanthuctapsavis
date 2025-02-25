package com.example.sd_95_polo_store_be.common.Mapper;

import org.modelmapper.ModelMapper;

public class EntityMapper {
    private ModelMapper modelMapper;

    public EntityMapper(ModelMapper modelMapper) {
        this.modelMapper = modelMapper;
    }

    public EntityMapper() {
        this.modelMapper = new ModelMapper();
    }


    public <E, R> R toR(E entity, Class<R> targetClass) {
        return modelMapper.map(entity, targetClass);
    }

    public <R, E> E toEntity(R target, Class<E> entityClass) {
        return modelMapper.map(target, entityClass);
    }
}
