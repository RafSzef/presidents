package com.presidents.service.president;

import com.presidents.model.entity.President;

import java.util.List;

public interface PresidentService {

    List<President> getAllPresidents();
    President savePresident(President president);
}
