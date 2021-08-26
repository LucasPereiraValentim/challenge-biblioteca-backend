package br.com.bibliotecabackend.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import br.com.bibliotecabackend.model.Usuario;
import br.com.bibliotecabackend.repository.UsuarioRepository;

@Service
@Component
public class ImplementsUserDetailsService implements UserDetailsService{
	
	@Autowired
	private UsuarioRepository usuarioRepository;
	
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		
		//Consulta no banco para realizar verificação para liberar acesso
		
		Usuario usuarioConsultado = usuarioRepository.findByLogin(username);
		
		if (usuarioConsultado == null) {
			throw new UsernameNotFoundException("Usuário não existe");
		} else {
			return new User(usuarioConsultado.getLogin(), usuarioConsultado.getSenha(), usuarioConsultado.getAuthorities());
		}
		
		
	}

}
