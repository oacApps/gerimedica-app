package com.mmr.gerimedicaapp.domain.mapper;

import com.mmr.gerimedicaapp.domain.entity.Temperature;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper
public interface TemperatureMapper {
    TemperatureMapper INSTANCE = Mappers.getMapper(TemperatureMapper.class);

    Temperature toEntity(com.mmr.gerimedicaapp.domain.model.Temperature temperature);
    com.mmr.gerimedicaapp.domain.model.Temperature toModel(Temperature temperature);

    List<Temperature> toEntityList(List<com.mmr.gerimedicaapp.domain.model.Temperature> temperatures);
    List<com.mmr.gerimedicaapp.domain.model.Temperature> toModelList(List<Temperature> temperatures);
}
