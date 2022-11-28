package com.escualos.core.domain.competition;


import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Builder(toBuilder = true)
@Data
@EqualsAndHashCode
public class Race {
    int distance;
    Style style;
}
