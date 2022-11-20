package com.escualos.api.competition;


import com.escualos.domain.competition.Competition;
import com.escualos.domain.competition.Race;
import com.escualos.domain.competition.Session;
import com.escualos.domain.competition.Style;
import com.escualos.model.CompetitionDTO;
import com.escualos.model.RaceDTO;
import com.escualos.model.SessionDTO;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.time.LocalDate;
import java.time.OffsetDateTime;
import java.time.ZoneOffset;
import java.util.Map;

@ExtendWith(SpringExtension.class)
class CompetitionMapperTest {

    static Competition competition;
    static CompetitionDTO competitionDTO;

    @BeforeAll
    static void beforeAll() {
        Race race1 = Race.builder().style(Style.FOUR_STYLES).distance(100).build();
        Race race2 = Race.builder().style(Style.BACKSTROKE).distance(200).build();
        Session session = Session.builder()
                .name("session").date(LocalDate.of(2022, 10, 3))
                .races(Map.of(0, race1, 1, race2))
                .build();
        competition = Competition.builder()
                .title("Competition")
                .reference("competition-ref")
                .maxInscriptionsPerSwimmer(2)
                .inscriptionLimitDate(OffsetDateTime.of(2022, 10, 2, 10, 0, 0, 0, ZoneOffset.UTC))
                .competitionDay(LocalDate.of(2022, 10, 2))
                .location("lugar x")
                .description("description")
                .sessions(Map.of(0, session))
                .build();
        RaceDTO race3 = RaceDTO.builder().style(RaceDTO.StyleEnum.FOUR_STYLES).distance(100).build();
        RaceDTO race4 = RaceDTO.builder().style(RaceDTO.StyleEnum.BACKSTROKE).distance(200).build();
        SessionDTO sessionDto = SessionDTO.builder()
                .name("session").date(LocalDate.of(2022, 10, 3))
                .races(Map.of("0", race3, "1", race4))
                .build();
        competitionDTO = CompetitionDTO.builder()
                .title("Competition")
                .reference("competition-ref")
                .maxInscriptionsPerSwimmer(2)
                .inscriptionLimitDate(OffsetDateTime.of(2022, 10, 2, 10, 0, 0, 0, ZoneOffset.UTC))
                .competitionDay(LocalDate.of(2022, 10, 2))
                .location("lugar x")
                .description("description")
                .sessions(Map.of("0", sessionDto))
                .build();
    }

    @Test
    void CompetitionToDtoUnitTest() {
        CompetitionDTO dto = CompetitionMapper.INSTANCE.toDto(competition);

        Assertions.assertEquals(competitionDTO, dto);
        Assertions.assertEquals(competitionDTO.getSessions().get("0"), dto.getSessions().get("0"));
        Assertions.assertEquals(competitionDTO.getSessions().get("0").getRaces().get("1"), dto.getSessions().get("0").getRaces().get("1"));

    }

    @Test
    void CompetitionToEntityUnitTest() {
        Competition entity = CompetitionMapper.INSTANCE.toEntity(competitionDTO);

        Assertions.assertEquals(competition, entity);
        Assertions.assertEquals(competition.getSessions().get(0), entity.getSessions().get(0));
        Assertions.assertEquals(competition.getSessions().get(0).getRaces().get(0), entity.getSessions().get(0).getRaces().get(0));

    }

}