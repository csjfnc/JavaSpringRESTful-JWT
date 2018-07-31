package com.algamoneyapi.service;

import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import com.algamoneyapi.model.Lancamento;
import com.algamoneyapi.repository.LancamentoRepository;

@Service
public class LancamentoService {

	@Autowired
	private LancamentoRepository lancamentoRepository;
	
	//LISTA TODOS OS LANCAMENTOS
	public List<Lancamento> listarService(){
		return lancamentoRepository.findAll();
	}
	
	//SALVA LANCAMENTO NO BANCO
	public Lancamento salvarService(Lancamento lancamento){
		return lancamentoRepository.save(lancamento);		
	}
	
	//BUSCA LANCAMENTO PELO CODIGO
	public Lancamento buscarPeloCodigoService(Long codigo){
		Lancamento lancamento = lancamentoRepository.findById(codigo).get();
		
		if(lancamento == null){
			throw new EmptyResultDataAccessException(1);
		}
		
		return lancamento;
	}
	
	//ATUALIZAR LANCAMENTO
	public Lancamento atualizarService(Long codigo, Lancamento lancamento){
		Lancamento lancamentoBanco = buscarPeloCodigoService(codigo);
		BeanUtils.copyProperties(lancamento, lancamentoBanco, "codigo");
		lancamentoRepository.save(lancamentoBanco);
		return lancamentoBanco;
	}
}
