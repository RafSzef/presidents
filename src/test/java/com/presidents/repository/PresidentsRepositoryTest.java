package com.presidents.repository;

import com.presidents.model.entity.President;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@ExtendWith(SpringExtension.class)
@DataJpaTest
class PresidentsRepositoryTest {

    @Autowired
    PresidentsRepository presidentsRepository;

    @Autowired
    TestEntityManager testEntityManager;

    @Test
    void shouldFindAllPresidents_when_searchedForAll() {
        //given
        testEntityManager.persist(President.builder()
                .name("President")
                .surname("PresidentSurname")
                .politicalParty("politicalPartry")
                .build());
        //when
        var presidents = presidentsRepository.findAll();
        //then
        Assertions.assertEquals(1, presidents.size());
    }

    @Test
    void shouldFindPresidents_whenNameGivenCorrectly() {
        //given
        testEntityManager.persist(President.builder()
                .name("President")
                .surname("PresidentSurname")
                .politicalParty("politicalPartry")
                .build());
        testEntityManager.persist(President.builder()
                .name("President")
                .surname("PresidentSurname2")
                .politicalParty("politicalParty")
                .build());
        //when
        var presidents = presidentsRepository.findPresidentByName("President");
        //then
        Assertions.assertEquals(2, presidents.size());
    }
}