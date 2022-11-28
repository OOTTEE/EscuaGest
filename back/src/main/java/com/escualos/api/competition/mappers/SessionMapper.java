package com.escualos.api.competition.mappers;

import com.escualos.api.model.SessionDTO;
import com.escualos.core.domain.competition.Session;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface SessionMapper {

    SessionMapper INSTANCE = Mappers.getMapper(SessionMapper.class);

    SessionDTO toDto(Session session);

    Session toEntity(SessionDTO sessionDTO);

}
