package br.com.bibliotecabackend.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.bibliotecabackend.model.Categoria;

public interface CategoriaRepository extends JpaRepository<Categoria, Long>{

}
