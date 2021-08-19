package br.com.bibliotecabackend.service;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import br.com.bibliotecabackend.exception.RecursoNaoEncontrado;
import br.com.bibliotecabackend.exception.TituloException;
import br.com.bibliotecabackend.model.Obra;
import br.com.bibliotecabackend.repository.RepositoryObra;

@Service
public class ObraService {
	
	@Autowired
	private RepositoryObra repositoryObra;
	
	@Transactional // Executado durante um transação
	public Obra salvar(Obra obra) {
		
		boolean obraCadastrada = repositoryObra.verificarTitulo(obra.getTitulo());
		
		if (obraCadastrada) {
			throw new TituloException("Já existe um obra cadastrada com este título");
		} else {
			
			for (int i = 0; i < obra.getAutores().size(); i++) {
				obra.getAutores().get(i).setObra(obra);
			}
			
			return repositoryObra.save(obra);
		}
		
	}
	
	
	public Page<Obra> getListaObra(Pageable pageable){
		
		Page<Obra> obras = repositoryObra.findAll(pageable);
		
		if (obras.isEmpty()) {
			throw new RecursoNaoEncontrado("Lista está Vázia");
		} else {
			return obras;
		}
	}
}
