package br.com.bibliotecabackend.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import br.com.bibliotecabackend.models.Obra;

@Repository
public interface RepositoryObra extends JpaRepository<Obra, Long>{
	
	@Query(value = "SELECT CASE WHEN COUNT(o) > 0 THEN true ELSE false END FROM Obra o WHERE o.titulo = :titulo")
	boolean verificarTitulo(@Param("titulo") String titulo);
	
	
}
