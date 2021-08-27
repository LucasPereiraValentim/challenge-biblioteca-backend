package br.com.bibliotecabackend.service;

import java.io.IOException;
import java.util.Collections;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

@Service
public class TokenAuthenticationService {

	// TOKEN expira em 10 dias
	static final long EXPIRATION_TIME = 864_000_000;

	static final String SECRET = "mySecret";

	static final String TOKEN_PREFIX = "Bearer";

	static final String HEADER_STRING = "Authorization";

	public void addAuthentication(HttpServletResponse response, String userName) throws Exception {

		String JWT = Jwts.builder().setSubject(userName)
				.setExpiration(new Date(System.currentTimeMillis() + EXPIRATION_TIME))
				.signWith(SignatureAlgorithm.HS512, SECRET).compact();

		// Add no cabeçalho do HTTP
		response.addHeader(HEADER_STRING, TOKEN_PREFIX + " " + JWT);

	}

	public Authentication getAuthentication(HttpServletRequest request, HttpServletResponse response) throws IOException, io.jsonwebtoken.ExpiredJwtException {

		// "PEGA" o TOKEN no cabeçalho da requsição
		String token = request.getHeader(HEADER_STRING);
		try {
			if (token != null) {
				Claims infoUsuario = Jwts.parser().setSigningKey(SECRET)
						.parseClaimsJws(token.replace(TOKEN_PREFIX, "").trim()) 
						.getBody();

				if (infoUsuario.getSubject() != null) {
					System.out.println(infoUsuario.getExpiration());
					return new UsernamePasswordAuthenticationToken(infoUsuario.getSubject(), null, Collections.emptyList());
				} 
			}

		} catch (io.jsonwebtoken.ExpiredJwtException e) {
			response.getOutputStream().println("Seu token expirou");
		}

		return null;
	}

}
