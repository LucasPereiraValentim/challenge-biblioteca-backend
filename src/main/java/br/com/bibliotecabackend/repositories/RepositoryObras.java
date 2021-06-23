package br.com.bibliotecabackend.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.bibliotecabackend.models.Obras;

@Repository
public interface RepositoryObras extends JpaRepository<Obras, Long>{
	
}
