package br.com.alura.forum.config.security;

import java.util.Date;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import br.com.alura.forum.modelo.Usuario;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

@Service
public class TokenService {

	@Value("${forum.jwt.expiration}")
	private Long expiration;
	
	@Value("${forum.jwt.secret}")
	private String secret;

	public String createToken(final Authentication authenticate) {
		final Usuario usuario = (Usuario) authenticate.getPrincipal();
		final Date hoje = new Date();
		final Date expira = new Date(hoje.getTime() + expiration);
		
		return Jwts.builder()
				.setIssuer("FORUM DA ALURA")
				.setSubject(usuario.getId().toString())
				.setIssuedAt(hoje)
				.setExpiration(expira)
				.signWith(SignatureAlgorithm.HS256, secret)
				.compact();
		
	}

	public boolean isValidToken(final String token) {
		try {
			Jwts.parser().setSigningKey(this.secret).parseClaimsJws(token);
			return true;
		} catch (final Exception e) {
			return false;
		}
	}

	public Long getId(final String token) {
		final Claims claims = Jwts.parser().setSigningKey(this.secret).parseClaimsJws(token).getBody();
		return Long.parseLong(claims.getSubject());
	}

}
