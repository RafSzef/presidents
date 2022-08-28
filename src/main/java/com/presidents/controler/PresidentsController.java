package com.presidents.controler;

import com.presidents.model.entity.President;
import com.presidents.repository.PresidentsRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

//import static com.presidents.repository.PresidentsRepository.presidentRepository;

@RequestMapping("presidents")
@RestController
@RequiredArgsConstructor
public class PresidentsController {

    private final PresidentsRepository presidentRepository;

    @GetMapping("all")
    public List<President> getAll() {
        return presidentRepository.findAll();
    }

//    @PostMapping("add-new")
//    public President addPresident(@RequestBody President president) {
//        presidentRepository.add(president);
//        return presidentRepository.get((int) president.getId());
//    }
//
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
