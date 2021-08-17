package br.com.bibliotecabackend.service;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.bibliotecabackend.exception.ObraException;
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
			throw new ObraException("Já existe um obra cadastrada com este título");
		} else {
			return repositoryObra.save(obra);
		}
		
	}
}
