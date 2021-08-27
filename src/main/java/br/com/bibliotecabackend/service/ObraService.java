package br.com.bibliotecabackend.service;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.annotation.ReadOnlyProperty;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import br.com.bibliotecabackend.exception.RecursoNaoEncontrado;
import br.com.bibliotecabackend.exception.TituloException;
import br.com.bibliotecabackend.model.Obra;
import br.com.bibliotecabackend.repository.ObraRepository;

@Service
public class ObraService {
	
	@Autowired
	private ObraRepository obraRepository;
	
	@Transactional // Executado durante um transação
	public Obra salvar(Obra obra) {
		
		boolean tituloEmUso = obraRepository.verificarTitulo(obra.getTitulo());
		
		if (tituloEmUso) {
			throw new TituloException("Já existe uma obra cadastrada com este título");
		} else {
			
			for (int i = 0; i < obra.getAutores().size(); i++) {
				obra.getAutores().get(i).setObra(obra);
			}
			
			return obraRepository.save(obra);
		}
		
	}
	
	@ReadOnlyProperty
	public Page<Obra> getListaObra(Pageable pageable){
		
		Page<Obra> obras = obraRepository.findAll(pageable);
		
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
		if (obraRepository.existsById(obraId)) {
			boolean tituloEmUso = obraRepository.verificarTitulo(obra.getTitulo());
			if (tituloEmUso) {
				throw new TituloException("Já existe uma obra cadastrada com este título");
			} else {
				Obra obraParaSalvar = obraRepository.findById(obraId).get();
				obraParaSalvar.setTitulo(obra.getTitulo());
				obraParaSalvar.setEditora(obra.getEditora());
				obraParaSalvar.setFoto(obra.getFoto());
				for (int i = 0; i < obraParaSalvar.getAutores().size(); i++) {
					obraParaSalvar.getAutores().get(i).setNome(obra.getAutores().get(i).getNome());;
					obraParaSalvar.getAutores().get(i).setObra(obra);
				}
				return obraRepository.save(obraParaSalvar);
			}
		} else {
			throw new RecursoNaoEncontrado("Informe um id válido para atualizar");
		}
	}

	@Transactional
	public String excluir(Long obraId) {
		
		if (!obraRepository.existsById(obraId)) {
			throw new RecursoNaoEncontrado("Informe o id para excluir um registro");
		} else {
			obraRepository.deleteById(obraId);
			return "Obra excluída com sucesso";
		}
	}
	
	@ReadOnlyProperty
	public Obra obterObra(Long obraId) {
		
		if (obraRepository.existsById(obraId)) {
			Obra obra = obraRepository.findById(obraId).get();
			return obra;
		} else {
			throw new RecursoNaoEncontrado("Obra não encontrada");
		}
		
		
	}

	public List<Obra> pesquisar(String titulo) {
		
		List<Obra> listaPesquisa = obraRepository.findLikeByTitulo(titulo);
		
		if (listaPesquisa.isEmpty()) {
			throw new RecursoNaoEncontrado("Não existe itens para esta pesquisa");
		}
		return listaPesquisa;
	}
	
	
}
