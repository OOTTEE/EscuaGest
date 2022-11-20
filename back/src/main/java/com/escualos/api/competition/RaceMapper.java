package com.escualos.api.competition;

import com.escualos.domain.competition.Race;
import com.escualos.model.RaceDTO;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface RaceMapper {

    RaceMapper INSTANCE = Mappers.getMapper(RaceMapper.class);

    RaceDTO toDto(Race race);

    Race toEntity(RaceDTO raceDTO);

}
