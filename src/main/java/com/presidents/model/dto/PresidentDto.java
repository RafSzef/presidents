package com.presidents.model.dto;

import lombok.*;

import java.sql.Timestamp;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class PresidentDto {

    private long id;

    private String name;

    private String surname;

    private Timestamp termFrom;

    private Timestamp termTo;

    private String politicalParty;
}
