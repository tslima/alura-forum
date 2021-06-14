package br.com.alura.forum.config.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import br.com.alura.forum.repositories.UsuarioRepository;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {
	
	@Autowired private UsuarioRepository usuarioRepository;

	@Override
	public UserDetails loadUserByUsername(final String username) throws UsernameNotFoundException {
		return usuarioRepository.findByEmail(username)
				.map(i->i)
				.orElseThrow(() -> new UsernameNotFoundException(""));
	}

}
