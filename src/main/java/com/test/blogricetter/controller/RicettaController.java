package com.test.blogricetter.controller;


import com.test.blogricetter.model.Categoria;
import com.test.blogricetter.model.Ricetta;
import com.test.blogricetter.repository.CategoriaRepository;
import com.test.blogricetter.repository.RicettaRepository;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.Banner;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/")
public class RicettaController {
    @Autowired
    private RicettaRepository ricettaRepository;
    @Autowired
    private CategoriaRepository categoriaRepository;
    // mostra ricette
    @GetMapping
    public String home(Model model){
        List<Ricetta> ricetteList=ricettaRepository.findAllByOrderByDataCreazioneDesc();
        model.addAttribute("ricette",ricetteList);
        return "homepage";
    }
    // admin
    @GetMapping("/admin")
    public String admin(Model model){
        List<Ricetta> ricetteList=ricettaRepository.findAll();
        model.addAttribute("ricette",ricetteList);
        return "admin";
    }

    // CRUD RICETTE
    // mostra ricetta
    @GetMapping("/ricette/{id}")
    public String show(@PathVariable("id") Integer id, Model model){
        Optional<Ricetta> result=ricettaRepository.findById(id);
        if (result.isPresent()){
            model.addAttribute("ricetta",result.get());
            return "ricette/detail";
        }else{
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Ricetta con id " + id + " non trovata");

        }

    }
    // crea ricette
    @GetMapping("/create")
    public String create(Model model) {
        List<Categoria> categoryList = categoriaRepository.findAll();
        model.addAttribute("categoryList", categoryList);

        model.addAttribute("ricetta", new Ricetta());
        return "ricette/form"; // template
    }

    @PostMapping("/create")
    public String doCreate(@Valid @ModelAttribute("ricetta") Ricetta formRicetta,
                           BindingResult bindingResult, Model model) {

        if (bindingResult.hasErrors()) {


            return "ricette/form";
        }
        LocalDate dataCreazione = LocalDate.now();
        formRicetta.setDataCreazione(dataCreazione);
        ricettaRepository.save(formRicetta);
        return "redirect:/";
    }
    // edita ricetta
    @GetMapping("/edit/{id}")
    public String edit(@PathVariable("id") Integer id , Model model){
        Optional<Ricetta> ricetta = ricettaRepository.findById(id);
        if (ricetta.isPresent()) {
            List<Categoria> categoryList = categoriaRepository.findAll();
            model.addAttribute("categoryList", categoryList);

            model.addAttribute("ricetta", ricetta.get());
            return "ricette/edit";
        } else {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Ricetta con id " + id + " non trovata");
        }

    }

    @PostMapping("/edit/{id}")
    public String doEdit(@PathVariable Integer id, @Valid @ModelAttribute("ricetta") Ricetta formRicetta,
                         BindingResult bindingResult, Model model) {
        Ricetta ricetta=ricettaRepository.findById(id).get();
        // salvo l id
        formRicetta.setId(ricetta.getId());
        // valido i dati
        if (bindingResult.hasErrors()) {
            return "/ricette/edit";
        }
        LocalDate dataCreazione = LocalDate.now();
        formRicetta.setDataCreazione(dataCreazione);
        ricettaRepository.save(formRicetta);
        return "redirect:/";
    }
    // cancella ricetta

    @PostMapping("/delete/{id}")
    public String deleteById(@PathVariable Integer id) {
        ricettaRepository.deleteById(id);
        return "redirect:/";
    }
}
