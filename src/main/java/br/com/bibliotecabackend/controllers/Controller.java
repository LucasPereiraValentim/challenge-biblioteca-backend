package br.com.bibliotecabackend.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import br.com.bibliotecabackend.models.Obra;
import br.com.bibliotecabackend.repositories.RepositoryObra;

@RestController
@RequestMapping(value = "/obras")
public class Controller {
	
	@Autowired
	RepositoryObra repositoryObra;
	
	@PostMapping(produces = "application/text")
	public ResponseEntity<String> salvar(@RequestBody Obra obra) {
		
		if (!repositoryObra.verificarTitulo(obra.getTitulo())) {
			for (int i = 0; i < obra.getAutores().size(); i++) {
				obra.getAutores().get(i).setObra(obra);
			}
			repositoryObra.save(obra);
			return new ResponseEntity<String>("Obra salva com sucesso", HttpStatus.CREATED);	
		}
		
		return new ResponseEntity<String>("Esta obra já está cadastrada", HttpStatus.OK);
	}
	
	@GetMapping(value = "/")
	public ResponseEntity<List<Obra>> getObras(){
		
		List<Obra> listaObras = repositoryObra.findAll();
		
		return new ResponseEntity<List<Obra>>(listaObras, HttpStatus.OK);
	}
	
	@PutMapping(value = "/{id}")
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
	
	@DeleteMapping(value = "excluirObra")
	@ResponseBody
	public ResponseEntity<String> excluir(@RequestParam Long id){
		
		repositoryObra.deleteById(id);
		
		return new ResponseEntity<String>("Excluido com sucesso!", HttpStatus.OK);
	}
	
}
