package br.com.bibliotecabackend.service;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.annotation.ReadOnlyProperty;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import br.com.bibliotecabackend.exception.RecursoNaoEncontrado;
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

	@Transactional
	public Categoria salvar(Categoria categoria) {
		
		if (!obraRepository.existsById(categoria.getObra().getId())) {
			throw new RecursoNaoEncontrado("Informe o id da obra");
		}
		
		Obra obra = obraRepository.findById(categoria.getObra().getId()).get();
		
		categoria.setObra(obra);
		
		return categoriaRepository.save(categoria);
	}
	
	@ReadOnlyProperty
	public Page<Categoria> listar(Pageable pageable) {
		
		Page<Categoria> lista = categoriaRepository.findAll(pageable);
		
		if (lista.isEmpty()) {
			throw new RecursoNaoEncontrado("Lista vázia");
		}
		return lista;
	}
	
}
