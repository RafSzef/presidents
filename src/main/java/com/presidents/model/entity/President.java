package com.presidents.model.entity;

import lombok.*;

import javax.persistence.*;
import java.sql.Timestamp;
@Entity
@Table(name = "President")
@Setter
@Getter
@Builder
@ToString
@NoArgsConstructor
@EqualsAndHashCode
@AllArgsConstructor
public class President {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    private String name;

    private String surname;

    private Timestamp termFrom;

    private Timestamp termTo;

    private String politicalParty;
}




