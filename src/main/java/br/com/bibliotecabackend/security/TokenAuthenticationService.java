package br.com.bibliotecabackend.security;

import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;

import br.com.bibliotecabackend.models.Usuario;
import br.com.bibliotecabackend.repositories.RepositoryUsuario;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

@Component
public class TokenAuthenticationService {
	
	static final long EXPIRATION_TIME = 860_000_000;
	
	static final String SECRET = "mySecret";
	
	static final String TOKEN_PREFIX = "Bearer";
	
	static final String HEADER_STRING = "Authorization";
	
	@Autowired
	private RepositoryUsuario repositoryUsuario;
	
	
	public void addAuthentication(HttpServletResponse response, String login) throws Exception{
		String JWT = Jwts.builder()
				.setSubject(login)
				.setExpiration(new Date(System.currentTimeMillis() + EXPIRATION_TIME))
				.signWith(SignatureAlgorithm.HS512, SECRET).compact();
		
		String token = TOKEN_PREFIX + " " + JWT;
		
		//Add no cabeçalho do HTTP
		response.addHeader(HEADER_STRING, token);
		
		//Retorna o TOKEN ao cliente
		response.getWriter().write("{\""+ HEADER_STRING + "\": \"" + token + "\"}");
	}
	
	public Authentication getAuthentication(HttpServletRequest request) {
		
		// "PEGA" o TOKEN no cabeçalho da requsição
		String token = request.getHeader(HEADER_STRING);
		
		if (token != null) {
			String usuario = Jwts.parser().setSigningKey(SECRET)
					.parseClaimsJws(token.replace(TOKEN_PREFIX, "")) //Retorna o TOKEN
					.getBody().getSubject();
			
			if (usuario != null) {
				
				Usuario user = repositoryUsuario.verificarUsuario(usuario);
				
				if (user != null) {
					return new UsernamePasswordAuthenticationToken(user.getLogin(), user.getSenha(), user.getAuthorities());
				}
			}
			
		}
		
		
		return null;
	}
	
	
	
	
	
	
}
