package br.com.alura.forum.controllers.dtos;

import javax.validation.constraints.NotEmpty;

import org.hibernate.validator.constraints.Length;

import br.com.alura.forum.modelo.Topico;

public class AtualizaTopicoForm {
	
	@NotEmpty @Length(min = 5)
	private String titulo;
	
	@NotEmpty @Length(min = 10)
	private String mensagem;

	public Topico atualiza(final Topico topico ) {
		topico.setTitulo(this.titulo);
		topico.setMensagem(this.mensagem);
		return topico;
	}

	public String getTitulo() {
		return titulo;
	}

	public void setTitulo(final String titulo) {
		this.titulo = titulo;
	}

	public String getMensagem() {
		return mensagem;
	}

	public void setMensagem(final String mensagem) {
		this.mensagem = mensagem;
	}
	
	

}
