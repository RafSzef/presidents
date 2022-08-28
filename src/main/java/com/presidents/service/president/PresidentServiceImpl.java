package com.presidents.service.president;

import com.presidents.model.dto.PresidentDto;
import com.presidents.model.entity.President;
import com.presidents.model.mapper.PresidentMapper;
import com.presidents.repository.PresidentsRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
@RequiredArgsConstructor
public class PresidentServiceImpl implements PresidentService {
    private final PresidentsRepository presidentsRepository;

    @Override
    public List<PresidentDto> getAllPresidents() {
        return presidentsRepository.findAll()
                .stream()
                .map(PresidentMapper::toDto)
                .toList();
    }

    @Override
    public PresidentDto savePresident(PresidentDto presidentDto) {
        return PresidentMapper.toDto(presidentsRepository.save(PresidentMapper.toEntity(presidentDto)));
    }

    @Override
    @Transactional
    public PresidentDto updatePresident(PresidentDto presidentDto) {
        presidentsRepository
                .findById(presidentDto.getId())
                .ifPresent(president -> {
                    president.setName(presidentDto.getName());
                    president.setSurname(presidentDto.getSurname());
                    president.setTermFrom(presidentDto.getTermFrom());
                    president.setTermTo(presidentDto.getTermTo());
                    president.setPoliticalParty(presidentDto.getPoliticalParty());
                });
        return PresidentMapper.toDto(presidentsRepository.getReferenceById(presidentDto.getId()));
    }

    @Override
    @Transactional
    public PresidentDto updatePresidentPartial(PresidentDto presidentDto) {
        presidentsRepository.findById(presidentDto.getId())
                .ifPresent(p -> {
                    if (presidentDto.getName() != null) {
                        p.setName(presidentDto.getName());
                    }
                    if (presidentDto.getSurname() != null) {
                        p.setSurname(presidentDto.getSurname());
                    }
                    if (presidentDto.getTermFrom() != null) {
                        p.setTermFrom(presidentDto.getTermFrom());
                    }
                    if (presidentDto.getTermTo() != null) {
                        p.setTermTo(presidentDto.getTermTo());
                    }
                    if (presidentDto.getPoliticalParty() != null) {
                        p.setPoliticalParty(presidentDto.getPoliticalParty());
                    }
                });
        return PresidentMapper.toDto(presidentsRepository.getReferenceById(presidentDto.getId()));
    }
}
