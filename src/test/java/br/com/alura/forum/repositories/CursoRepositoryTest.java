package br.com.alura.forum.repositories;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.ActiveProfiles;

import br.com.alura.forum.modelo.Curso;

@DataJpaTest
@ActiveProfiles("test")
public class CursoRepositoryTest {

	@Autowired private CursoRepository cursoRepository;
	
	@Test
	public void testFindByNome() {
		final String nome = "HTML 5";
		final Curso curso = cursoRepository.findByNome(nome);
		
		assertNotNull(curso);
		assertEquals(nome, curso.getNome());;
		
	}
}
