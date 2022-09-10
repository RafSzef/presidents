package com.presidents.service.president;

import com.presidents.exception.exceptions.EntityNotFoundException;
import com.presidents.model.dto.PresidentDto;
import com.presidents.model.mapper.PresidentMapper;
import com.presidents.repository.PresidentsRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import static com.presidents.exception.messeges.ExceptionMessages.ENTITY_FOR_PROVIDED_ID_NOT_EXIST;
import static com.presidents.exception.messeges.ExceptionMessages.ENTITY_FOR_PROVIDED_PARAMETER_NOT_EXIST;
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
        Set<PresidentDto> presidents = presidentsRepository.findPresidentByName(name).stream()
                .map(PresidentMapper::toDto)
                .collect(Collectors.toSet());
        if (presidents.isEmpty()){
            throw new EntityNotFoundException(ENTITY_FOR_PROVIDED_PARAMETER_NOT_EXIST.getMessage());
        }else return presidents;
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
        var president = presidentsRepository.findById(presidentDto.getId());
        if (president.isPresent()){
            president.map(p -> {
                        p.setName(presidentDto.getName());
                        p.setSurname(presidentDto.getSurname());
                        p.setTermFrom(presidentDto.getTermFrom());
                        p.setTermTo(presidentDto.getTermTo());
                        p.setPoliticalParty(presidentDto.getPoliticalParty());
                        return PresidentMapper.toDto(p);
                    });
        }else {
            return PresidentMapper.toDto(presidentsRepository.save(PresidentMapper.toEntity(presidentDto)));
        }
        return presidentDto;
    }

    @Override
    public PresidentDto updatePresidentPartial(PresidentDto presidentDto) {
        return presidentsRepository.findById(presidentDto.getId())
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
                }).orElseThrow(() -> new EntityNotFoundException(ENTITY_FOR_PROVIDED_ID_NOT_EXIST.getMessage()));
    }

    @Override
    public void deletePresident(Long id) {
        presidentsRepository.deleteById(id);
    }


}
