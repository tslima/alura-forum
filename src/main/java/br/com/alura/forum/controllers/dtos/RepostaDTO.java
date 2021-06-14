package br.com.alura.forum.controllers.dtos;

import java.time.LocalDateTime;

import br.com.alura.forum.modelo.Resposta;

public class RepostaDTO {

	private Long id;
	private String mensagem;
	private LocalDateTime dataCriacao;
	private String nomeAutor;

	public RepostaDTO(final Resposta resposta) {
		this.id = resposta.getId();
		this.mensagem = resposta.getMensagem();
		this.dataCriacao = resposta.getDataCriacao();
		this.nomeAutor = resposta.getAutor().getNome();
	}

	public Long getId() {
		return id;
	}

	public void setId(final Long id) {
		this.id = id;
	}

	public String getMensagem() {
		return mensagem;
	}

	public void setMensagem(final String mensagem) {
		this.mensagem = mensagem;
	}

	public LocalDateTime getDataCriacao() {
		return dataCriacao;
	}

	public void setDataCriacao(final LocalDateTime dataCriacao) {
		this.dataCriacao = dataCriacao;
	}

	public String getNomeAutor() {
		return nomeAutor;
	}

	public void setNomeAutor(final String nomeAutor) {
		this.nomeAutor = nomeAutor;
	}
	
	
}
