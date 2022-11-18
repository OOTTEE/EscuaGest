package com.escualos.domain.competition;


import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class Race {
    int distance;
    Style style;
}
