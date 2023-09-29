package com.test.blogricetter.repository;

import com.test.blogricetter.model.Categoria;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoriaRepository extends JpaRepository<Categoria,Integer> {
}
