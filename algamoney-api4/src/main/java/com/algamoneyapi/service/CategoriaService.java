package com.algamoneyapi.service;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import com.algamoneyapi.model.Categoria;
import com.algamoneyapi.repository.CategoriaRepository;

@Service
public class CategoriaService {

	@Autowired
	private CategoriaRepository categoriaRepository;
	
	public Categoria atualizarService(Long codigo, Categoria categoria){
		Categoria categoriaBd = buscarPeloCodigoService(codigo);
		BeanUtils.copyProperties(categoria, categoriaBd, "codigo");
		
		return categoriaRepository.save(categoriaBd);
	}
	
	public Categoria buscarPeloCodigoService(Long codigo){
		Categoria categoria = categoriaRepository.findById(codigo).get();
		if(categoria == null)
			throw new EmptyResultDataAccessException(1);
		return categoria;
	}
}
