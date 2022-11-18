package com.escualos.domain.competition;

import lombok.Builder;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Map;

@Document
@Builder
@Data
public class Competition {
    @Id
    private String id;
    @NotNull
    @Max(80)
    private String title;
    @NotNull
    @Indexed(unique = true)
    @Max(80)
    @Min(7)
    @Pattern(regexp = "^[a-zA-Z0-9-_]{7,80}$")
    private String reference;
    private String description;
    private LocalDate competitionDay;
    @NotNull
    private LocalDateTime inscriptionLimitDate;
    @NotNull
    private int maxInscriptionsPerSwimmer;
    private String location;
    private Map<Integer, Session> sessions;
}
