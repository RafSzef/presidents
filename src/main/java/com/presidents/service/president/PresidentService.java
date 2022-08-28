package com.presidents.service.president;

import com.presidents.model.dto.PresidentDto;
import com.presidents.model.entity.President;

import java.util.List;

public interface PresidentService {

    List<PresidentDto> getAllPresidents();
    PresidentDto savePresident(PresidentDto presidentDto);
    PresidentDto updatePresident(PresidentDto presidentDto);
    PresidentDto updatePresidentPartial(PresidentDto presidentDto);
    void deletePresident(Long id);
}
