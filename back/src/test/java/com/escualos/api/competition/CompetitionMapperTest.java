package com.escualos.api.competition;


import com.escualos.IntegrationTest;
import com.escualos.api.competition.mappers.CompetitionMapper;
import com.escualos.api.competition.mappers.CompetitionMapperImpl;
import com.escualos.api.competition.mappers.RaceMapperImpl;
import com.escualos.api.competition.mappers.SessionMapperImpl;
import com.escualos.api.model.CompetitionDTO;
import com.escualos.api.model.RaceDTO;
import com.escualos.api.model.SessionDTO;
import com.escualos.core.competition.Competition;
import com.escualos.core.competition.Race;
import com.escualos.core.competition.Session;
import com.escualos.core.competition.Style;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.time.ZonedDateTime;
import java.util.Map;

@ExtendWith(SpringExtension.class)
@SpringBootTest(classes = {CompetitionMapperImpl.class, SessionMapperImpl.class, RaceMapperImpl.class})
class CompetitionMapperTest extends IntegrationTest {
    Competition competition;
    CompetitionDTO competitionDTO;
    private final String competitionDay = "2022-10-01T00:00:00.000000Z";
    private final String limitDate = "2022-10-01T10:50:00.000000Z";

    @Autowired
    CompetitionMapper mapper;

    @BeforeEach
    void beforeEach() {
        Race race1 = Race.builder().style(Style.FOUR_STYLES).distance(100).build();
        Race race2 = Race.builder().style(Style.BACKSTROKE).distance(200).build();
        Session session = Session.builder()
                .name("session").date(ZonedDateTime.parse(competitionDay))
                .races(Map.of(0, race1, 1, race2))
                .build();
        this.competition = Competition.builder()
                .title("Competition")
                .reference("competition-ref")
                .maxInscriptionsPerSwimmer(2)
                .inscriptionLimitDate(ZonedDateTime.parse(limitDate))
                .competitionDate(ZonedDateTime.parse(competitionDay))
                .location("lugar x")
                .description("description")
                .sessions(Map.of(0, session))
                .build();
        RaceDTO race3 = RaceDTO.builder().style(RaceDTO.StyleEnum.FOUR_STYLES).distance(100).build();
        RaceDTO race4 = RaceDTO.builder().style(RaceDTO.StyleEnum.BACKSTROKE).distance(200).build();
        SessionDTO sessionDto = SessionDTO.builder()
                .name("session").date("2022-10-01T00:00:00Z")
                .races(Map.of("0", race3, "1", race4))
                .build();
        this.competitionDTO = CompetitionDTO.builder()
                .title("Competition")
                .reference("competition-ref")
                .maxInscriptionsPerSwimmer(2)
                .inscriptionLimitDate("2022-10-01T10:50:00Z")
                .competitionDate("2022-10-01T00:00:00Z")
                .location("lugar x")
                .description("description")
                .sessions(Map.of("0", sessionDto))
                .build();
    }

    @Test
    void CompetitionToDtoUnitTest() {
        CompetitionDTO dto = mapper.toDto(competition);

        Assertions.assertEquals(competitionDTO, dto);
    }

    @Test
    void CompetitionToEntityUnitTest() {
        Competition entity = mapper.toEntity(competitionDTO);

        Assertions.assertEquals(competition, entity);
    }

}