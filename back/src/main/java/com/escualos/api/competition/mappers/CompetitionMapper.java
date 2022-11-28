package com.escualos.api.competition.mappers;

import com.escualos.api.model.CompetitionDTO;
import com.escualos.core.domain.competition.Competition;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface CompetitionMapper {
    CompetitionMapper INSTANCE = Mappers.getMapper(CompetitionMapper.class);

    CompetitionDTO toDto(Competition competition);

    Competition toEntity(CompetitionDTO competitionDTO);

}
