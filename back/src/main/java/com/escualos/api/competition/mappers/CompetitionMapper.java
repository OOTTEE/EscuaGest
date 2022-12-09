package com.escualos.api.competition.mappers;

import com.escualos.api.model.CompetitionDTO;
import com.escualos.api.utils.Constants;
import com.escualos.core.competition.Competition;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(uses = {SessionMapper.class}, componentModel = "spring")
public interface CompetitionMapper {

    @Mapping(target = "competitionDate",source = "competitionDate", dateFormat = Constants.DATE_TIME_FORMAT)
    @Mapping(target = "inscriptionLimitDate",source = "inscriptionLimitDate", dateFormat = Constants.DATE_TIME_FORMAT)
    CompetitionDTO toDto(Competition competition);

    @Mapping(target = "competitionDate",source = "competitionDate", dateFormat = Constants.DATE_TIME_FORMAT)
    @Mapping(target = "inscriptionLimitDate",source = "inscriptionLimitDate", dateFormat = Constants.DATE_TIME_FORMAT)
    Competition toEntity(CompetitionDTO competitionDTO);

}
