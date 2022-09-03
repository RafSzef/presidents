package com.presidents.service.president;

import com.presidents.model.dto.PresidentDto;
import com.presidents.model.entity.President;
import com.presidents.repository.PresidentsRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Bean;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.Arrays;
import java.util.List;

@ExtendWith(SpringExtension.class)
@DataJpaTest
class PresidentServiceImplTest {

    @TestConfiguration
    static class PresidentServiceTestConfig {

        @Bean
        PresidentService presidentService(PresidentsRepository presidentsRepository) {
            return new PresidentServiceImpl(presidentsRepository);
        }
    }

    @MockBean
    PresidentsRepository presidentsRepository;

    @Autowired
    PresidentService presidentService;

    @Test
    void shouldReturnAllPresidents_whenSearchingForAllPresidents() {
        //given
        List<President> presidents = Arrays.asList(
                President.builder()
                        .name("President")
                        .surname("PresidentSurname")
                        .politicalParty("politicalPartry")
                        .build(),
                President.builder()
                        .name("President")
                        .surname("PresidentSurname2")
                        .politicalParty("politicalParty")
                        .build());
        Mockito.when(presidentsRepository.findAll()).thenReturn(presidents);
        //when
        var allPresidents = presidentService.getAllPresidents();
        //then
        Assertions.assertEquals(2, allPresidents.size());
    }
}