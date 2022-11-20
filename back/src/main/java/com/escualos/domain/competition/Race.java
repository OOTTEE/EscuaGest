package com.escualos.domain.competition;


import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Builder
@Data
@EqualsAndHashCode
public class Race {
    int distance;
    Style style;
}
