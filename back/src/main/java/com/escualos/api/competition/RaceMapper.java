package com.escualos.api.competition;

import com.escualos.domain.competition.Race;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;
import org.openapitools.model.RaceDTO;
import org.openapitools.model.RacesDTO;

@Mapper
public interface RaceMapper {

    RaceMapper INSTANCE = Mappers.getMapper(RaceMapper.class);

    RaceDTO toDto(Race race);

    Race toEntity(RacesDTO racesDTO);

}
