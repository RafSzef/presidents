package com.presidents.controler;

import com.presidents.model.dto.PresidentDto;
import com.presidents.repository.PresidentsRepository;
import com.presidents.service.president.PresidentService;
import lombok.RequiredArgsConstructor;
import org.h2.engine.Mode;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@Controller
@RequiredArgsConstructor
public class PresidentsControllerThymeleaf {

    private final PresidentsRepository presidentsRepository;
    private final PresidentService presidentService;

    @GetMapping("/")
    public String getIndex(Model model, @RequestParam(name = "form", required = false, defaultValue = "false") boolean form) {
        model.addAttribute("presidents", presidentsRepository.findAll());
        model.addAttribute("presidentDto", new PresidentDto());
        model.addAttribute("form", form);
        return "index";
    }

    @PostMapping("/save")
    @ResponseStatus(HttpStatus.CREATED)
    public String addPresident(@ModelAttribute(name = "presidentDto") PresidentDto presidentDto, Model model) {
        presidentService.savePresident(presidentDto);
        return "redirect:/";
    }
}
