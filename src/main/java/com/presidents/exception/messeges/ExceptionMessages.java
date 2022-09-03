package com.presidents.exception.messeges;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum ExceptionMessages {

    ENTITY_FOR_PROVIDED_ID_NOT_EXIST("Encja dla podanego Id nie istnieje");

    private final String message;
}
