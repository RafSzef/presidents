package com.presidents.controler;

import com.presidents.model.entity.President;
import com.presidents.repository.PresidentsRepository;
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
    public List<President> getAll() {
        return presidentService.getAllPresidents();
    }

    @PostMapping("add-new")
    public President addPresident(@RequestBody President president) {
        return presidentService.savePresident(president);
    }

//    @PutMapping("update")
//    public President updatePresident(@RequestBody President president) {
//        if (presidentRepository.size() <= president.getId()) {
//            president.setId(presidentRepository.size());
//            presidentRepository.add(president);
//        } else {
//            presidentRepository.set((int) president.getId(), president);
//        }
//        return presidentRepository.get((int) president.getId());
//    }
//
//    @PatchMapping("update")
//    public President updatePresidentPart(@RequestBody President president){
//        President p = presidentRepository.get((int) president.getId());
//        if (president.getName() != null){
//            p.setName(president.getName());
//        }
//        if (president.getSurname() != null){
//            p.setSurname(president.getSurname());
//        }
//        if (president.getTermFrom() != null){
//            p.setTermFrom(president.getTermFrom());
//        }
//        if (president.getTermTo() != null){
//            p.setTermTo(president.getTermTo());
//        }
//        if (president.getPoliticalParty() != null){
//            p.setPoliticalParty(president.getPoliticalParty());
//        }
//        return presidentRepository.get((int) president.getId());
//    }
//    @DeleteMapping("delete/{id}")
//    public String delete (@PathVariable int id){
//        presidentRepository.remove(id);
//        return "deleted";
//    }
}
