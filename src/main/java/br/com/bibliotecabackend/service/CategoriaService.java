package br.com.bibliotecabackend.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.bibliotecabackend.model.Categoria;
import br.com.bibliotecabackend.model.Obra;
import br.com.bibliotecabackend.repository.CategoriaRepository;
import br.com.bibliotecabackend.repository.ObraRepository;

@Service
public class CategoriaService {
	
	@Autowired
	private CategoriaRepository categoriaRepository;

	@Autowired
	private ObraRepository obraRepository;

	public Categoria salvar(Categoria categoria, Long obraId) {
		
		Obra obra = obraRepository.findById(obraId).get();
		
		categoria.setObra(obra);
		
		return categoriaRepository.save(categoria);
	}
	
}
