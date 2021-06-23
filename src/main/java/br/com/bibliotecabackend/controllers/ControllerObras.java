package br.com.bibliotecabackend.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import br.com.bibliotecabackend.models.Obras;
import br.com.bibliotecabackend.repositories.RepositoryObras;

@RestController
@RequestMapping(value = "/obras")
public class ControllerObras {
	
	@Autowired
	RepositoryObras repositoryObras;
	
	@PostMapping(value = "/salvarObras")
	@ResponseBody
	public ResponseEntity<Obras> salvar(@RequestBody Obras obras) {
		
		Obras obra = repositoryObras.save(obras);
		
		
		return new ResponseEntity<Obras>(obra, HttpStatus.CREATED);	
	}
	
	@GetMapping(value = "/listarObras")
	public ResponseEntity<List<Obras>> getObras(){
		return null;
	}
	
}
