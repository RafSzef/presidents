package com.presidents.controler;

import com.presidents.model.dto.PresidentDto;
import org.hamcrest.Matcher;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.sql.Timestamp;
import java.time.Instant;
import java.util.Arrays;
import java.util.List;

import static com.presidents.util.TestUtils.toJson;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
class PresidentsControllerIT {

    @Autowired
    private MockMvc mockMvc;

    @Test
    public void whenPostRequestForPresidentSave_thenCorrectResponse() throws Exception {
        //given
        var presidentDto = PresidentDto.builder()
                .name("President")
                .surname("PresidentSurname")
                .politicalParty("PoliticalParty")
                .termFrom(Timestamp.from(Instant.ofEpochMilli(15_000_000)))
                .termTo(Timestamp.from(Instant.ofEpochMilli(15_000_000_00)))
                .build();
        //when
        mockMvc.perform(post("/presidents/add-new")
                        .content(toJson(presidentDto))
                        .contentType(MediaType.APPLICATION_JSON))
                //then
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.name", Matchers.equalTo("President")));
    }

    @Test
    public void whenPostRequestForPresidentSaveAndIncorrectName_ThenIncorrectResponse() throws Exception {
        //given
        var presidentDto = PresidentDto.builder()
                .name(null)
                .build();
        //when
        mockMvc.perform(post("/presidents/add-new")
                        .content(toJson(presidentDto))
                        .contentType(MediaType.APPLICATION_JSON))
                //then
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.message", Matchers.containsInAnyOrder("Name is required")));

    }
}