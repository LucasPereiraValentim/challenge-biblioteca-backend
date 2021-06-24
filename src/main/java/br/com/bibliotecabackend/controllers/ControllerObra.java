package br.com.bibliotecabackend.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
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
@RequestMapping(value = "/obra")
public class ControllerObra {
	
	@Autowired
	RepositoryObra repositoryObra;
	
	@PostMapping(value = "/salvarObra")
	@ResponseBody
	public ResponseEntity<String> salvar(@RequestBody Obra obra) {
		
		if (!repositoryObra.verificarTitulo(obra.getTitulo())) {
			repositoryObra.save(obra);
			return new ResponseEntity<String>("Obra salva com sucesso", HttpStatus.CREATED);	
		}
		
		return new ResponseEntity<String>("Esta obra já está cadastrada", HttpStatus.OK);
	}
	
	@GetMapping(value = "/listarObras")
	@ResponseBody
	public ResponseEntity<List<Obra>> getObras(){
		
		List<Obra> listaObras = repositoryObra.findAll();
		
		return new ResponseEntity<List<Obra>>(listaObras, HttpStatus.OK);
	}
	
	@PutMapping(value = "/atualizarObra")
	@ResponseBody
	public ResponseEntity<String> atualizar(@RequestBody Obra obra){
		
		if (obra.getId() != null){
			repositoryObra.saveAndFlush(obra);
			return new ResponseEntity<String>("Atualizado com sucesso", HttpStatus.OK);
		}	
		return new ResponseEntity<String>("Não foi possível atualizar", HttpStatus.OK);
	}
	
	@DeleteMapping(value = "excluirObra")
	@ResponseBody
	public ResponseEntity<String> excluir(@RequestParam Long id){
		
		repositoryObra.deleteById(id);
		
		return new ResponseEntity<String>("Excluido com sucesso!", HttpStatus.OK);
	}
	
}
