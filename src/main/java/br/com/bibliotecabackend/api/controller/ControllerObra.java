package br.com.bibliotecabackend.api.controller;

import javax.validation.Valid;

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

import br.com.bibliotecabackend.api.dto.ObraDTO;
import br.com.bibliotecabackend.api.input.ObraInput;
import br.com.bibliotecabackend.api.mapper.ObraMapper;
import br.com.bibliotecabackend.model.Obra;
import br.com.bibliotecabackend.repository.RepositoryObra;
import br.com.bibliotecabackend.service.ObraService;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping(value = "/obras")
public class ControllerObra {
	
	@Autowired
	private RepositoryObra repositoryObra;
	
	@Autowired
	private ObraService obraService;
	
	@Autowired
	private ObraMapper obraMapper;
	
	@PostMapping
	public ResponseEntity<ObraDTO> salvar(@Valid @RequestBody ObraInput obraInput) {
		
		Obra obraToEntidade = obraMapper.toEntidade(obraInput);
		
		Obra obraSalva = obraService.salvar(obraToEntidade);
		
		return new ResponseEntity<ObraDTO>(obraMapper.toObraDTO(obraSalva), HttpStatus.CREATED);
	}
	
	@GetMapping(value = "/", produces = "application/json")
	public ResponseEntity<Page<Obra>> getObras(@PageableDefault(page = 0, size = 5, sort = "titulo", direction = Direction.ASC) Pageable pageable){
		
		Page<Obra> listaObras = repositoryObra.findAll(pageable);
		
		if (listaObras.isEmpty()) {
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		} else {
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
	
	@GetMapping(value = "/obra/{id}", produces = "application/json")
	public ResponseEntity<Obra> getObra(@PathVariable Long id){
		
		if (id != null) {
			Obra obra = repositoryObra.findById(id).get();
			if (obra != null) {
				return new ResponseEntity<Obra>(obra, HttpStatus.OK);
			} else {
				return new ResponseEntity<>(HttpStatus.NOT_FOUND);
			}
		} else {
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}
	}
	
	
}
