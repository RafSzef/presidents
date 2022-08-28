package com.presidents.controler;

import com.presidents.model.dto.PresidentDto;
import com.presidents.model.entity.President;
import com.presidents.service.president.PresidentService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

//import static com.presidents.repository.PresidentsRepository.presidentRepository;

@RequestMapping("presidents")
@RestController
@RequiredArgsConstructor
public class PresidentsController {

    private final PresidentService presidentService;

    @GetMapping("all")
    public List<PresidentDto> getAll() {
        return presidentService.getAllPresidents();
    }

    @PostMapping("add-new")
    public PresidentDto addPresident(@RequestBody PresidentDto presidentDto) {
        return presidentService.savePresident(presidentDto);
    }

    @PutMapping("update")
    public PresidentDto updatePresident(@RequestBody PresidentDto presidentDto) {

        return presidentService.updatePresident(presidentDto);
    }

    @PatchMapping("update")
    public PresidentDto updatePresidentPart(@RequestBody PresidentDto presidentDto) {
        return presidentService.updatePresidentPartial(presidentDto);
    }
//    @DeleteMapping("delete/{id}")
//    public String delete (@PathVariable int id){
//        presidentRepository.remove(id);
//        return "deleted";
//    }
}
