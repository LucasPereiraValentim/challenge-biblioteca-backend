package br.com.bibliotecabackend.api.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
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
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import br.com.bibliotecabackend.api.dto.ObraDTO;
import br.com.bibliotecabackend.api.input.ObraInput;
import br.com.bibliotecabackend.api.mapper.ObraMapper;
import br.com.bibliotecabackend.model.Obra;
import br.com.bibliotecabackend.service.ObraService;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping(value = "/obras")
public class ControllerObra {
	
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
	
	@GetMapping(value = "/")
	@CacheEvict(value = "cache-obras", allEntries = true)
	@CachePut(value = "cache-obras")
	public ResponseEntity<List<ObraDTO>> getObras(@PageableDefault(page = 0, size = 5, sort = "titulo", direction = Direction.ASC) Pageable pageable){
		
		Page<Obra> listaObras = obraService.getListaObra(pageable);
		
		return new ResponseEntity<List<ObraDTO>>(obraMapper.toListDTO(listaObras), HttpStatus.OK);
	}
	
	@PutMapping(value = "/{obraId}")
	public ResponseEntity<ObraDTO> atualizar(@PathVariable Long obraId, @Valid @RequestBody ObraInput obraInput){
		Obra obraToEntidade = obraMapper.toEntidade(obraInput);
		
		Obra obraSalva = obraService.atualizar(obraId, obraToEntidade);
		
		ObraDTO obraDTO = obraMapper.toObraDTO(obraSalva);
		
		return new ResponseEntity<ObraDTO>(obraDTO, HttpStatus.OK);
	}
	
	@DeleteMapping(value = "/{obraId}")
	@ResponseStatus(HttpStatus.OK)
	public String excluir(@PathVariable Long obraId){
		return obraService.excluir(obraId);
	}
	
	@GetMapping(value = "/{obraId}")
	@ResponseStatus(HttpStatus.OK)
	@CacheEvict(value = "cache-obra", allEntries = true)
	@CachePut(value = "cache-obra")
	public ObraDTO getObra(@PathVariable Long obraId){	
		Obra obra = obraService.obterObra(obraId);
		ObraDTO obraDTO = obraMapper.toObraDTO(obra);
		return obraDTO;
	}
	
	@GetMapping(value = "/pesquisa/{titulo}")
	@CacheEvict(value = "cache-pesquisa", allEntries = true)
	@CachePut(value = "cache-pesquisa")
	public ResponseEntity<List<ObraDTO>> pesquisarPorTitulo(@PathVariable String titulo){
		List<ObraDTO> listaPesquisada = obraMapper.toListDTO(obraService.pesquisar(titulo));
		return new ResponseEntity<List<ObraDTO>>(listaPesquisada, HttpStatus.OK);
	}
	
}
