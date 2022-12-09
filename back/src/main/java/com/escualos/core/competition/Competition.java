package com.escualos.core.competition;

import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Positive;
import javax.validation.constraints.Size;
import java.time.ZonedDateTime;
import java.util.Map;

@Document
@Builder(toBuilder = true)
@Data
@EqualsAndHashCode
public class Competition {
    @Id
    private String id;
    @Size(min = 5, max = 80)
    @NotNull
    private String title;
    @NotNull
    @Indexed(unique = true)
    @Size(min = 5, max = 80)
    @Pattern(regexp = "^[a-zA-Z0-9-_]+$")
    private String reference;
    private String description;
    private ZonedDateTime competitionDate;
    @NotNull
    private ZonedDateTime inscriptionLimitDate;
    @NotNull
    @Positive
    private int maxInscriptionsPerSwimmer;
    private String location;
    private Map<Integer, Session> sessions;

}
