package br.com.alura.forum.controllers;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.alura.forum.config.security.TokenService;
import br.com.alura.forum.controllers.dtos.LoginFormDTO;
import br.com.alura.forum.controllers.dtos.TokenDTO;

@RestController
@RequestMapping("auth")
@Profile(value={"prd", "test"})
public class AutenticacaoController {

	@Autowired private AuthenticationManager authenticationManager;
	@Autowired private TokenService tokenService;

	@PostMapping
	public ResponseEntity<?> autentica(@RequestBody @Valid final LoginFormDTO form){

		final UsernamePasswordAuthenticationToken authentication = form.convert();
		try {
			final Authentication authenticate = authenticationManager.authenticate(authentication);
			
			final String token = tokenService.createToken(authenticate);
			
			return ResponseEntity.ok(new TokenDTO(token, "Bearer"));
		} catch(final AuthenticationException e) {
			return ResponseEntity.badRequest().build();
		}

	}
}
