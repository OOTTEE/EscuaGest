package com.escualos.domain.competition;

import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.validation.constraints.Max;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;
import java.util.Map;


@Builder
@Data
@EqualsAndHashCode
public class Session {
    @NotNull
    LocalDate date;
    @NotNull
    @Max(50)
    String name;
    Map<Integer, Race> races;
}
