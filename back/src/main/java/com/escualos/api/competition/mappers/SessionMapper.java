package com.escualos.api.competition.mappers;

import com.escualos.api.model.SessionDTO;
import com.escualos.api.utils.Constants;
import com.escualos.core.competition.Session;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(uses = {RaceMapper.class}, componentModel = "spring")
public interface SessionMapper {

    @Mapping(target = "date",source = "date", dateFormat = Constants.DATE_TIME_FORMAT)
    SessionDTO toDto(Session session);

    @Mapping(target = "date",source = "date", dateFormat = Constants.DATE_TIME_FORMAT)
    Session toEntity(SessionDTO sessionDTO);

}
