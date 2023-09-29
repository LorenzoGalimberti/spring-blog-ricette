package com.test.blogricetter.repository;

import com.test.blogricetter.model.Ricetta;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RicettaRepository extends JpaRepository<Ricetta,Integer> {
}
