package br.com.bibliotecabackend.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import br.com.bibliotecabackend.models.Obras;

@Repository
public interface RepositoryObras extends JpaRepository<Obras, Long>{
	
	@Query(value = "select o from Obras o where o.titulo = :obra")
	public boolean verificarObras(@Param("obra") String obra);
	
	
}
