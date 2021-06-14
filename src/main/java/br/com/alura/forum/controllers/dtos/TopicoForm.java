package br.com.alura.forum.controllers.dtos;

import javax.validation.constraints.NotEmpty;

import org.hibernate.validator.constraints.Length;

import br.com.alura.forum.modelo.Curso;
import br.com.alura.forum.modelo.Topico;
import br.com.alura.forum.repositories.CursoRepository;

public class TopicoForm {

	@NotEmpty @Length(min = 5)
	private String titulo;
	
	@NotEmpty @Length(min = 10)
	private String mensagem;
	
	@NotEmpty
	private String nomeCurso;


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
	public String getNomeCurso() {
		return nomeCurso;
	}
	public void setNomeCurso(final String nomeCurso) {
		this.nomeCurso = nomeCurso;
	}
	public Topico converter(final CursoRepository cursoRepository) {
		
		final Curso curso = cursoRepository.findByNome(nomeCurso);
		return new Topico(titulo, mensagem, curso);
		
	}
}
