package com.presidents.model.dto;

import lombok.*;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.sql.Timestamp;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class PresidentDto {

    private long id;

    @NotNull(message = "Name is required")
    @Size(min =1 , max = 255, message = "Incorrect name length")
    private String name;

    private String surname;

    private Timestamp termFrom;

    private Timestamp termTo;

    private String politicalParty;
}
