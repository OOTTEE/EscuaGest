package com.escualos.api.swimmer;

import com.escualos.domain.swimmer.Swimmer;
import com.escualos.model.CreateSwimmerDTO;
import com.escualos.model.SwimmerDTO;

public class SwimmerMapper {
    public static SwimmerDTO toSwimmerDTO(Swimmer swimmer) {
        return new SwimmerDTO()
                .birthday(swimmer.birthday)
                .id(swimmer.id)
                .licence(swimmer.licence)
                .username(swimmer.username);
    }

    public static Swimmer toSwimmer(CreateSwimmerDTO createSwimmerDTO) {
        return Swimmer.builder()
                .birthday(createSwimmerDTO.getBirthday())
                .licence(createSwimmerDTO.getLicence())
                .username(createSwimmerDTO.getUsername())
                .build();
    }
}
