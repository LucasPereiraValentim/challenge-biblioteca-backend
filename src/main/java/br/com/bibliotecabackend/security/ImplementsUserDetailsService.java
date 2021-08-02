package br.com.bibliotecabackend.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import br.com.bibliotecabackend.models.Usuario;
import br.com.bibliotecabackend.repositories.RepositoryUsuario;

@Service
@Component
public class ImplementsUserDetailsService implements UserDetailsService{
	
	@Autowired
	private RepositoryUsuario repositoryUsuario;
	
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		
		//Consulta no banco para realizar verificação para liberar acesso
		
		Usuario usuario = repositoryUsuario.verificarUsuario(username);
		
		if (usuario == null) {
			throw new UsernameNotFoundException("Usuário não existe");
		} else {
			return new User(usuario.getLogin(), usuario.getSenha(), usuario.getAuthorities());
		}
		
		
	}

}
