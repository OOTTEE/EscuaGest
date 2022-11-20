package com.escualos.api.competition;

import com.escualos.domain.competition.Competition;
import com.escualos.model.CompetitionDTO;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface CompetitionMapper {
    CompetitionMapper INSTANCE = Mappers.getMapper(CompetitionMapper.class);

    CompetitionDTO toDto(Competition competition);

    Competition toEntity(CompetitionDTO competitionDTO);

}
