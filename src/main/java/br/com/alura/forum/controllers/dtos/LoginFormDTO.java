package br.com.alura.forum.controllers.dtos;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;

public class LoginFormDTO {
	
	private String email;
	private String password;
	
	public String getEmail() {
		return email;
	}
	public void setEmail(final String email) {
		this.email = email;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(final String password) {
		this.password = password;
	}
	public UsernamePasswordAuthenticationToken convert() {
		return new UsernamePasswordAuthenticationToken(email, password);
	}
	
	

}
