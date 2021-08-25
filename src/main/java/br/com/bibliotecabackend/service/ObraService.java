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
		
		boolean tituloEmUso = repositoryObra.verificarTitulo(obra.getTitulo());
		
		if (tituloEmUso) {
			throw new TituloException("Já existe uma obra cadastrada com este título");
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

	
	@Transactional //Excutado durante um transação
	public Obra atualizar(Long obraId, Obra obra) {
		
		// "Injeta" o id para relacionar os objetos na hora de salvar.
		obra.setId(obraId);
		
		//Verificar se o id existe no banco
		if (repositoryObra.existsById(obraId)) {
			boolean tituloEmUso = repositoryObra.verificarTitulo(obra.getTitulo());
			if (tituloEmUso) {
				throw new TituloException("Já existe uma obra cadastrada com este título");
			} else {
				Obra obraParaSalvar = repositoryObra.findById(obraId).get();
				obraParaSalvar.setTitulo(obra.getTitulo());
				obraParaSalvar.setEditora(obra.getEditora());
				obraParaSalvar.setFoto(obra.getFoto());
				for (int i = 0; i < obraParaSalvar.getAutores().size(); i++) {
					obraParaSalvar.getAutores().get(i).setNome(obra.getAutores().get(i).getNome());;
					obraParaSalvar.getAutores().get(i).setObra(obra);
				}
				return repositoryObra.save(obraParaSalvar);
			}
		} else {
			throw new RecursoNaoEncontrado("Informe um id válido para atualizar");
		}
	}
}
