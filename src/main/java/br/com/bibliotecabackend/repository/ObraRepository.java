package br.com.bibliotecabackend.repository;

import org.springframework.data.annotation.ReadOnlyProperty;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import br.com.bibliotecabackend.model.Obra;

@Repository
public interface ObraRepository extends JpaRepository<Obra, Long>{
	
	@ReadOnlyProperty
	@Query(value = "SELECT CASE WHEN COUNT(o) > 0 THEN true ELSE false END FROM Obra o WHERE o.titulo = :titulo")
	boolean verificarTitulo(@Param("titulo") String titulo);
	
	@ReadOnlyProperty
	@Query(value = "SELECT o FROM Obra o WHERE TRIM(o.titulo) LIKE %?1% ORDER BY ?#{#pageable}")
	Page<Obra> findByTitulo(String titulo, Pageable pageable);
	
	
	
}
