package br.com.alura.forum.controllers.dtos;

public class TokenDTO {

	private final String token;
	private final String tipo;

	public TokenDTO(final String token, final String tipo) {
		this.token = token;
		this.tipo = tipo;
	}

	public String getToken() {
		return token;
	}

	public String getTipo() {
		return tipo;
	}
	
	
}
