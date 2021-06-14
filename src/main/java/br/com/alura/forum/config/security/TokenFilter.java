package br.com.alura.forum.config.security;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.OncePerRequestFilter;

import br.com.alura.forum.modelo.Usuario;
import br.com.alura.forum.repositories.UsuarioRepository;

public class TokenFilter extends OncePerRequestFilter {

	private final TokenService tokenService;
	private final UsuarioRepository usuarioRepository;

	public TokenFilter(final TokenService tokenService, final UsuarioRepository usuarioRepository) {
		super();
		this.tokenService = tokenService;
		this.usuarioRepository = usuarioRepository;
	}

	@Override
	protected void doFilterInternal(final HttpServletRequest request, final HttpServletResponse response, final FilterChain filterChain)
			throws ServletException, IOException {
		
		final String token = this.getToken(request);
		
		final boolean valid = tokenService.isValidToken(token);
		System.out.println(valid);
		
		if (valid) {
			this.autentica(token);
		}
		
		System.out.println(valid);
		
		filterChain.doFilter(request, response);

	}

	private void autentica(final String token) {
		final Long id = tokenService.getId(token);
		final Usuario usuario = usuarioRepository.findById(id).get();
		final Authentication authentication = new UsernamePasswordAuthenticationToken(usuario, null, usuario.getAuthorities());
		SecurityContextHolder.getContext().setAuthentication(authentication );
	}

	private String getToken(final HttpServletRequest request) {
		
		final String token = request.getHeader("Authorization");
		
		if (token == null || token.isEmpty() || !token.startsWith("Bearer "))
			return null;

		return token.substring(7);
	}

}
