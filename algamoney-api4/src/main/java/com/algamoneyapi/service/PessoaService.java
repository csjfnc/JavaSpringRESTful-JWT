package com.algamoneyapi.service;

import java.util.Optional;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import com.algamoneyapi.model.Pessoa;
import com.algamoneyapi.repository.PessoaRepository;

@Service
public class PessoaService {

	@Autowired
	private PessoaRepository pessoaRepository;
	
	// recebe um objeto e um codigo, busca o objeto no banco.. faz uma copia do objeto que esta vindo para o objeto que veio do banco. 
	public Pessoa atualizarService(long codigo, Pessoa pessoa){
		Pessoa pessoaBd = buscarPeloCodigo(codigo);
		BeanUtils.copyProperties(pessoa, pessoaBd, "codigo");
		
		return pessoaRepository.save(pessoaBd);
	}	
	
	public Pessoa buscarPeloCodigo(long codigo){
		Optional<Pessoa> pessoa = pessoaRepository.findById(codigo);
		if(pessoa == null ){
			throw new EmptyResultDataAccessException(1);
		}		
		return pessoa.get();		
	}
	
	
}
