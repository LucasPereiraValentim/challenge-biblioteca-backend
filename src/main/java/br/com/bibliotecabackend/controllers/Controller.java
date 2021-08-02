package br.com.bibliotecabackend.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.bibliotecabackend.models.Obra;
import br.com.bibliotecabackend.repositories.RepositoryObra;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping(value = "/obras")
public class Controller {
	
	@Autowired
	RepositoryObra repositoryObra;
	
	@PostMapping(produces = "application/text")
	public ResponseEntity<String> salvar(@RequestBody Obra obra) {
		
		if (obra.getId() == null) {
			if (!repositoryObra.verificarTitulo(obra.getTitulo())) {
				for (int i = 0; i < obra.getAutores().size(); i++) {
					obra.getAutores().get(i).setObra(obra);
				}
				repositoryObra.save(obra);
				return new ResponseEntity<String>("Obra salva com sucesso", HttpStatus.CREATED);	
			}
		}
		
		return new ResponseEntity<String>("Esta obra já está cadastrada", HttpStatus.OK);
	}
	
	@GetMapping(value = "/", produces = "application/json")
	public ResponseEntity<Page<Obra>> getObras(@PageableDefault(page = 0, size = 5, sort = "titulo", direction = Direction.ASC) Pageable pageable){
		
		Page<Obra> listaObras = repositoryObra.findAll(pageable);
		
		if (listaObras.isEmpty()) {
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		} else {
			listaObras.forEach(obra -> System.out.println(obra));
			return new ResponseEntity<Page<Obra>>(listaObras, HttpStatus.OK);
		}
	}
	
	@PutMapping(value = "/{id}", produces = "application/text")
	public ResponseEntity<String> atualizar(@PathVariable Long id, @RequestBody Obra obra){
		
		if (id != null) {
			Obra obraPesquisada = repositoryObra.findById(id).get();
			obraPesquisada.setTitulo(obra.getTitulo());
			obraPesquisada.setEditora(obra.getEditora());
			obraPesquisada.setFoto(obra.getFoto());
			
			for (int i = 0; i < obra.getAutores().size(); i++) {
				obraPesquisada.getAutores().get(i).setObra(obraPesquisada);
				
			}
			repositoryObra.save(obraPesquisada);
			return new ResponseEntity<String>("Atualizado com sucesso!", HttpStatus.OK);
			
		}
		
		
		return new ResponseEntity<String>("Não foi possível atualizar", HttpStatus.NOT_FOUND);
	}
	
	@DeleteMapping(value = "/{id}", produces = "application/text")
	public ResponseEntity<String> excluir(@PathVariable Long id){
		
		if (id != null) {
			repositoryObra.deleteById(id);
			return new ResponseEntity<String>("Excluido com sucesso!", HttpStatus.OK);
		}
		
		return new ResponseEntity<String>("Não foi possível atualizar", HttpStatus.NOT_FOUND);
		
	}
	
}
