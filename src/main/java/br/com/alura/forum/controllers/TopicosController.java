package br.com.alura.forum.controllers;

import java.net.URI;
import java.util.Optional;

import javax.transaction.Transactional;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import br.com.alura.forum.controllers.dtos.AtualizaTopicoForm;
import br.com.alura.forum.controllers.dtos.DetalhesTopicoDTO;
import br.com.alura.forum.controllers.dtos.TopicoDTO;
import br.com.alura.forum.controllers.dtos.TopicoForm;
import br.com.alura.forum.exceptions.TopicoNotFoundException;
import br.com.alura.forum.modelo.Topico;
import br.com.alura.forum.repositories.CursoRepository;
import br.com.alura.forum.repositories.TopicoRepository;

@RestController
@RequestMapping("topicos")
public class TopicosController {
	
	@Autowired private TopicoRepository topicoRepository;
	@Autowired private CursoRepository cursoRepository;

	@GetMapping
	@Cacheable("listaDeTopicos")
	public Page<TopicoDTO> lista(@RequestParam(required = false) final String nomeCurso
			, @PageableDefault(page = 0, size = 10) final Pageable pageable){
		
		final Page<Topico> topicos = nomeCurso == null ?  
				topicoRepository.findAll(pageable) 
				: topicoRepository.findByCursoNome(nomeCurso,pageable);
				
		return TopicoDTO.convert(topicos);
	}
	
	@PostMapping
	@Transactional
	@CacheEvict(value="listaDeTopicos", allEntries = true)
	public ResponseEntity<TopicoDTO> cadastrar(@RequestBody @Valid final TopicoForm form, final UriComponentsBuilder uriBuilder) {
		final Topico topico = form.converter(cursoRepository);
		topicoRepository.save(topico);
		final URI uri = uriBuilder.path("/topicos/{id}").buildAndExpand(topico.getId()).toUri();
		return ResponseEntity.created(uri).body(new TopicoDTO(topico));
	}
	
	
	@GetMapping("{id}")
	public DetalhesTopicoDTO detalhar(@PathVariable final Long id) {
		return topicoRepository.findById(id).map(DetalhesTopicoDTO::new)
				.orElseThrow(TopicoNotFoundException::new);
	}
	
	@PutMapping("{id}")
	@Transactional
	@CacheEvict(value="listaDeTopicos", allEntries = true)
	public TopicoDTO atualiza(@PathVariable final Long id, @RequestBody @Valid final AtualizaTopicoForm form) {
		
		return topicoRepository.findById(id).map(old -> {
			final Topico topico =  form.atualiza(old);
			return new TopicoDTO(topico);
		}).orElseThrow(TopicoNotFoundException::new);
		
	}
	
	@DeleteMapping("{id}")
	@Transactional
	@CacheEvict(value="listaDeTopicos", allEntries = true)
	public void remote(@PathVariable final Long id ) {
		final Optional<Topico> opt = topicoRepository.findById(id);
		if (opt.isPresent()) {
			topicoRepository.deleteById(id);
		} else {
			throw new TopicoNotFoundException();
		}
	}
}
