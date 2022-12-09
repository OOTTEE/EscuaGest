package com.escualos.core.domain.competition;

import com.escualos.DBIntegrationTest;
import com.escualos.core.competition.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.data.mongo.DataMongoTest;
import org.springframework.data.domain.PageRequest;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import reactor.core.publisher.Flux;
import reactor.test.StepVerifier;

import javax.validation.ConstraintViolationException;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.time.ZonedDateTime;
import java.util.Arrays;
import java.util.Map;

@DataMongoTest(
        excludeAutoConfiguration = org.springframework.boot.autoconfigure.mongo.embedded.EmbeddedMongoAutoConfiguration.class
)
@ExtendWith(SpringExtension.class)
@TestPropertySource(properties = "spring.mongodb.embedded.version=5.0.6")
class CompetitionSrvImplTest extends DBIntegrationTest {

    private static Logger LOG = LoggerFactory.getLogger(CompetitionSrvImplTest.class);
    @Autowired
    CompetitionRepository competitionRepository;

    @Autowired
    CompetitionSrv competitionSrv;

    private Competition competition, competition2;
    private Session session, session2;
    private Race race1, race2, race3, race4;

    @BeforeEach
    void beforeEach() {
        race1 = createRace(Style.FOUR_STYLES, 100);
        race2 = createRace(Style.BACKSTROKE, 200);
        session = createSession("session name", 10, race1, race2);
        competition = createCompetition("Competition", "competition-ref", 2, 10, "lugar x", "description", Map.of(0, session));
        race3 = createRace(Style.BREASTSTROKE, 50);
        race4 = createRace(Style.FREESTYLE, 100);
        session2 = createSession("session name 2", 11, race3, race4);
        competition2 = createCompetition("Competition 2", "competition-ref-2", 1, 11, "lugar x 2", "description 2", Map.of(0, session2));
        competitionRepository.deleteAll().block();
        competitionRepository.saveAll(Arrays.asList(competition, competition2)).blockLast();
    }


    @Test
    void getAll() {
        StepVerifier.create(competitionSrv.getAll())
                .expectNext(competition)
                .expectNext(competition2)
                .verifyComplete();
    }

    @Test
    void getAllPaginated() {
        competitionRepository.deleteAll().block();
        competitionRepository.insert(Flux.range(0, 20)
                .map(i -> Competition.builder().title("Competition " + i)
                        .reference("comp-"+i)
                        .maxInscriptionsPerSwimmer(1)
                        .inscriptionLimitDate(ZonedDateTime.of(2022,10,1,0,0,0,0,ZoneOffset.UTC))
                        .build())).blockLast();
        StepVerifier.create(competitionSrv
                        .getPaginated(PageRequest.of(0, 8))
                        .flatMapMany(Flux::fromIterable)
                )
                .expectNextCount(8).verifyComplete();
        StepVerifier.create(competitionSrv
                        .getPaginated(PageRequest.of(1, 8))
                        .flatMapMany(Flux::fromIterable))
                .expectNextCount(8).verifyComplete();
        StepVerifier.create(competitionSrv
                        .getPaginated(PageRequest.of(2, 8))
                        .flatMapMany(Flux::fromIterable))
                .expectNextCount(4).verifyComplete();
    }

    @Test
    void getById() {
        StepVerifier.create(competitionSrv.getById(competition.getId()))
                .expectNext(competition)
                .verifyComplete();
    }

    @Test
    void getByRef() {
        StepVerifier.create(competitionSrv.getByRef("competition-ref-2"))
                .expectNext(competition2)
                .verifyComplete();
    }

    @Test
    void createAFullCompetitionSuccess() {
        final Race race = createRace(Style.FOUR_STYLES, 50);
        final Race race1 = createRace(Style.FREESTYLE, 200);
        final Race race2 = createRace(Style.BACKSTROKE, 400);
        final Race race3 = createRace(Style.BACKSTROKE, 100);

        final Session session = createSession("sesion-1", 10, race, race1);
        final Session session1 = createSession("sesion-2", 11, race2, race3);

        final Competition compe = createCompetition("Competition a", "competition-a", 2, 10, "lugar", "desc", Map.of(0, session, 1, session1));

        StepVerifier.create(competitionSrv.create(compe))
                .expectNext(compe)
                .verifyComplete();
    }

    @Test
    void throwValidationErrorToTryCreateACompetitionWithoutTitle() {
        final Competition com = Competition.builder()
                .reference("competition")
                .inscriptionLimitDate(ZonedDateTime.of(LocalDateTime.now(), ZoneOffset.UTC))
                .maxInscriptionsPerSwimmer(0)
                .build();
        StepVerifier.create(competitionSrv.create(com))
                .expectError(ConstraintViolationException.class)
                .verify();
    }

    @Test
    void throwValidationErrorToTryCreateACompetitionWithoutReference() {
        final Competition com = Competition.builder()
                .title("competition")
                .inscriptionLimitDate(ZonedDateTime.of(LocalDateTime.now(), ZoneOffset.UTC))
                .maxInscriptionsPerSwimmer(0)
                .build();
        StepVerifier.create(competitionSrv.create(com))
                .expectError(ConstraintViolationException.class)
                .verify();
    }

    @Test
    void throwValidationErrorToTryCreateACompetitionWithoutLimitDate() {
        final Competition com = Competition.builder()
                .title("competition")
                .reference("competition")
                .maxInscriptionsPerSwimmer(0)
                .build();
        StepVerifier.create(competitionSrv.create(com))
                .expectError(ConstraintViolationException.class)
                .verify();
    }

    @Test
    void throwValidationErrorToTryCreateACompetitionInscriptionLimitBePositive() {
        final Competition com = Competition.builder()
                .title("competition")
                .reference("competition")
                .inscriptionLimitDate(ZonedDateTime.of(LocalDateTime.now(), ZoneOffset.UTC))
                .maxInscriptionsPerSwimmer(-1)
                .build();
        StepVerifier.create(competitionSrv.create(com))
                .expectError(ConstraintViolationException.class)
                .verify();
    }

    @Test
    void save() {
        competition.setTitle("new Title");
        session.setName("new session name");
        race1.setDistance(9999);

        StepVerifier.create(competitionSrv.save(competition))
                .expectNextMatches(competition1 -> {
                    return "new Title".equals(competition1.getTitle())
                            && "new session name".equals(session.getName())
                            && 9999 == race1.getDistance();
                })
                .verifyComplete();
    }

    private static Competition createCompetition(String Competition, String reference, int maxInscriptionsPerSwimmer, int month, String lugar_x, String description, Map<Integer, Session> sessions) {
        return com.escualos.core.competition.Competition.builder()
                .title(Competition)
                .reference(reference)
                .maxInscriptionsPerSwimmer(maxInscriptionsPerSwimmer)
                .inscriptionLimitDate(ZonedDateTime.of(2022, month, 2, 10, 0, 0, 0, ZoneOffset.UTC))
                .competitionDate(ZonedDateTime.from(Instant.now()))
                .location(lugar_x)
                .description(description)
                .sessions(sessions)
                .build();
    }

    private static Session createSession(String session_name, int month, Race race1, Race race2) {
        return Session.builder()
                .name(session_name).date(ZonedDateTime.now())
                .races(Map.of(0, race1, 1, race2))
                .build();
    }

    private static Race createRace(Style fourStyles, int distance) {
        return Race.builder().style(fourStyles).distance(distance).build();
    }
}