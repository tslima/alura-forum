package br.com.alura.forum.controllers.dtos;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

import br.com.alura.forum.modelo.StatusTopico;
import br.com.alura.forum.modelo.Topico;

public class DetalhesTopicoDTO {
	
	private final Long id;
	private final String titulo;
    private final String mensagem;
    private final LocalDateTime dataCriacao;
    private final String nomeAutor;
    private final StatusTopico status;
    private final List<RepostaDTO> respostas;


	public DetalhesTopicoDTO(final Topico topico) {
		
		this.id = topico.getId();
        this.titulo = topico.getTitulo();
        this.mensagem = topico.getMensagem();
        this.dataCriacao = topico.getDataCriacao();
        this.nomeAutor = topico.getAutor().getNome();
        this.status = topico.getStatus();
        this.respostas = topico.getRespostas().stream().map(RepostaDTO::new).collect(Collectors.toList());
		
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


	public String getNomeAutor() {
		return nomeAutor;
	}


	public StatusTopico getStatus() {
		return status;
	}


	public List<RepostaDTO> getRespostas() {
		return respostas;
	}
	
	

}
