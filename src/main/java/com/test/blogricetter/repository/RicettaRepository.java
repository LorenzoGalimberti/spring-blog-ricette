package com.test.blogricetter.repository;

import com.test.blogricetter.model.Ricetta;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface RicettaRepository extends JpaRepository<Ricetta,Integer> {
    List<Ricetta> findAllByOrderByDataCreazioneDesc();
    List<Ricetta> findByTitoloContainingIgnoreCase(String titolo);

    List<Ricetta> findByTitoloContainingIgnoreCaseOrIngredientiContainingIgnoreCaseOrTestoRicettaContainingIgnoreCase(String titolo, String ingredienti, String testoRicetta);



}
