package com.escualos.api.competition;

import com.escualos.domain.competition.Session;
import com.escualos.model.SessionDTO;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface SessionMapper {

    SessionMapper INSTANCE = Mappers.getMapper(SessionMapper.class);

    SessionDTO toDto(Session session);

    Session toEntity(SessionDTO sessionDTO);

}
