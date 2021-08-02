package br.com.bibliotecabackend.repositories;

import org.springframework.data.annotation.ReadOnlyProperty;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import br.com.bibliotecabackend.models.Usuario;

@Repository
public interface RepositoryUsuario extends JpaRepository<Usuario, Long>{
	
	@ReadOnlyProperty
	@Query(value = "SELECT u FROM Usuario u WHERE u.login = :login")
	Usuario verificarUsuario(@Param(value = "login") String login);
	
}
