package com.escualos.core.competition;

import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.validation.constraints.Max;
import javax.validation.constraints.NotNull;
import java.time.ZonedDateTime;
import java.util.Map;


@Builder(toBuilder = true)
@Data
@EqualsAndHashCode
public class Session {
    @NotNull
    ZonedDateTime date;
    @NotNull
    @Max(50)
    String name;
    Map<Integer, Race> races;

}
