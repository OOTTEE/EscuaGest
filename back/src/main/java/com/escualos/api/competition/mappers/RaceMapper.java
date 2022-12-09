package com.escualos.api.competition.mappers;

import com.escualos.api.model.RaceDTO;
import com.escualos.core.competition.Race;
import org.mapstruct.Mapper;


@Mapper(componentModel = "spring")
public interface RaceMapper {

    RaceDTO toDto(Race race);

    Race toEntity(RaceDTO raceDTO);

}
