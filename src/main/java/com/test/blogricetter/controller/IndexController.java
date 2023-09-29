package com.test.blogricetter.controller;

import com.test.blogricetter.model.Ricetta;
import com.test.blogricetter.repository.RicettaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/")
public class IndexController {
    @Autowired
    private RicettaRepository ricettaRepository;
    @GetMapping
    public String redirectHome(){
        return "redirect:/ricette";
    }

    // admin
    @GetMapping("/admin")
    public String admin(@RequestParam(name = "keyword") Optional<String> searchKeyword,Model model){

        List<Ricetta> ricetteList;
        String keyword = "";

        // verifico se ho la stringa di ricerca
        if (searchKeyword.isPresent()) {
            keyword = searchKeyword.get();
            // devo usare il metodo del repository che fa la ricerca filtrata
            ricetteList = ricettaRepository.findByTitoloContainingIgnoreCase(keyword);
        } else {
            // recupero tutti gli User da database
            ricetteList = ricettaRepository.findAll();
        }

        model.addAttribute("ricette",ricetteList);
        model.addAttribute("keyword", keyword);
        return "admin";
    }

}
