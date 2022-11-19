package com.escualos.api.competition;

import com.escualos.domain.competition.Session;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;
import org.openapitools.model.SessionDTO;

@Mapper
public interface SessionMapper {

    SessionMapper INSTANCE = Mappers.getMapper(SessionMapper.class);

    SessionDTO toDto(Session session);

    Session toEntity(SessionDTO sessionDTO);

}
