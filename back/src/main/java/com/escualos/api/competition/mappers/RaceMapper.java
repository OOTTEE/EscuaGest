package com.escualos.api.competition.mappers;

import com.escualos.api.model.RaceDTO;
import com.escualos.core.domain.competition.Race;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface RaceMapper {

    RaceMapper INSTANCE = Mappers.getMapper(RaceMapper.class);

    RaceDTO toDto(Race race);

    Race toEntity(RaceDTO raceDTO);

}
