package com.presidents.service.president;

import com.presidents.model.dto.PresidentDto;
import com.presidents.model.mapper.PresidentMapper;
import com.presidents.repository.PresidentsRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import static java.util.Objects.nonNull;

@Service
@Transactional
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
    public Set<PresidentDto> findPresidentsByName(String name) {
        return presidentsRepository.findPresidentByName(name).stream()
                .map(PresidentMapper::toDto)
                .collect(Collectors.toSet());
    }

    @Override
    public Set<PresidentDto> findPresidentsByPoliticalParty(String politicalParty) {
        return (presidentsRepository.findPresidentByPoliticalParty(politicalParty)).stream()
                .map(PresidentMapper::toDto)
                .collect(Collectors.toSet());
    }

    @Override
    public PresidentDto savePresident(PresidentDto presidentDto) {
        return PresidentMapper.toDto(presidentsRepository.save(PresidentMapper.toEntity(presidentDto)));
    }

    @Override
    public PresidentDto updatePresident(PresidentDto presidentDto) {
        presidentsRepository
                .findById(presidentDto.getId())
                .map(president -> {
                    president.setName(presidentDto.getName());
                    president.setSurname(presidentDto.getSurname());
                    president.setTermFrom(presidentDto.getTermFrom());
                    president.setTermTo(presidentDto.getTermTo());
                    president.setPoliticalParty(presidentDto.getPoliticalParty());
                    return president;
                });
        return PresidentMapper.toDto(presidentsRepository.getReferenceById(presidentDto.getId()));
    }

    @Override
    public PresidentDto updatePresidentPartial(PresidentDto presidentDto) {
        presidentsRepository.findById(presidentDto.getId())
                .map(p -> {
                    if (nonNull(presidentDto.getName())) {
                        p.setName(presidentDto.getName());
                    }
                    if (nonNull(presidentDto.getSurname())) {
                        p.setSurname(presidentDto.getSurname());
                    }
                    if (nonNull(presidentDto.getTermFrom())) {
                        p.setTermFrom(presidentDto.getTermFrom());
                    }
                    if (nonNull(presidentDto.getTermTo())) {
                        p.setTermTo(presidentDto.getTermTo());
                    }
                    if (nonNull(presidentDto.getPoliticalParty())) {
                        p.setPoliticalParty(presidentDto.getPoliticalParty());
                    }
                    return PresidentMapper.toDto(p);
                });
        return PresidentMapper.toDto(presidentsRepository.getReferenceById(presidentDto.getId()));
    }

    @Override
    public void deletePresident(Long id) {
        presidentsRepository.deleteById(id);
    }


}
