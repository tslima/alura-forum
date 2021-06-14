package br.com.alura.forum.controllers.dtos;

import java.time.LocalDateTime;

import org.springframework.data.domain.Page;

import br.com.alura.forum.modelo.Topico;

public class TopicoDTO {

	private final Long id; 
	private final String titulo; 
	private final String mensagem; 
	private final LocalDateTime dataCriacao;

	

	public TopicoDTO(final Topico topico) {
		super();
		this.id = topico.getId();
		this.titulo = topico.getTitulo();
		this.mensagem = topico.getMensagem();
		this.dataCriacao = topico.getDataCriacao();
	}
	
	public Long getId() {
		return id;
	}
	public String getTitulo() {
		return titulo;
	}
	public String getMensagem() {
		return mensagem;
	}
	public LocalDateTime getDataCriacao() {
		return dataCriacao;
	}

	public static Page<TopicoDTO> convert(final Page<Topico> topicos) {
		return topicos.map(TopicoDTO::new);
	}



}
