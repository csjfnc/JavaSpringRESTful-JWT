package com.algamoneyapi.resources;

import java.util.List;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.algamoneyapi.event.ResursoCriadoEvent;
import com.algamoneyapi.model.Pessoa;
import com.algamoneyapi.repository.PessoaRepository;
import com.algamoneyapi.service.PessoaService;

@RestController
@RequestMapping("/pessoas")
public class PessoaResource {

	@Autowired
	private PessoaRepository pessoaRepository;
	
	@Autowired
	private ApplicationEventPublisher publisher;
	
	@Autowired
	private PessoaService pessoaService;
	
	@GetMapping
	public List<Pessoa> listar(){
		return pessoaRepository.findAll();
	}
	
	@PostMapping
	public ResponseEntity<Pessoa> criar(@Valid @RequestBody Pessoa pessoa, HttpServletResponse response){
		Pessoa pessoaSalva = pessoaRepository.save(pessoa);
		publisher.publishEvent(new ResursoCriadoEvent(this, response, pessoaSalva.getCodigo()));
		 return ResponseEntity.status(HttpStatus.CREATED).body(pessoaSalva);		
	}
	
	@GetMapping("/{codigo}")
	public ResponseEntity<Pessoa> buscarPeloCodigo(@PathVariable long codigo){
		Pessoa pessoaBd = pessoaService.buscarPeloCodigo(codigo);		
		return pessoaBd != null ? ResponseEntity.ok(pessoaBd) : ResponseEntity.ok().build();
	}

	@DeleteMapping("/{codigo}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void remover(@PathVariable long codigo){
		pessoaRepository.deleteById(codigo);
	}
	
	@PutMapping("/{codigo}")
	public ResponseEntity<Pessoa> atualizar(@PathVariable long codigo, @Valid @RequestBody Pessoa pessoa){
		Pessoa pessoaAtualizada = pessoaService.atualizarService(codigo, pessoa);		
		return ResponseEntity.ok(pessoaAtualizada);
	}
	
}










