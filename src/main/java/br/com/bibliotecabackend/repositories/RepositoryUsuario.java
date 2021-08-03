package br.com.bibliotecabackend.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import br.com.bibliotecabackend.models.Usuario;

@Repository
@Component
public interface RepositoryUsuario extends JpaRepository<Usuario, Long>{
	
	@Query(value = "SELECT u FROM Usuario u WHERE u.login = :login")
	Usuario findByLogin(@Param("login") String login);
	
	
	
}
