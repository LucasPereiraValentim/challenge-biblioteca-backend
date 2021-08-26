package br.com.bibliotecabackend.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import br.com.bibliotecabackend.model.Usuario;

@Repository
@Component
public interface UsuarioRepository extends JpaRepository<Usuario, Long>{
	
	@Query(value = "SELECT u FROM Usuario u WHERE u.login = :login")
	Usuario findByLogin(@Param("login") String login);
	
	
	
}
