package com.escualos.api.competition;

import com.escualos.domain.competition.Competition;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;
import org.openapitools.model.CompetitionDTO;

@Mapper
public interface CompetitionMapper {
    CompetitionMapper INSTANCE = Mappers.getMapper(CompetitionMapper.class);

    CompetitionDTO toDto(Competition competition);

    Competition toEntity(CompetitionDTO competitionDTO);
}
