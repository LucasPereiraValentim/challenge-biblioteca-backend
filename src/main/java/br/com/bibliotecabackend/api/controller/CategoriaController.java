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
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import br.com.bibliotecabackend.api.dto.CategoriaDTO;
import br.com.bibliotecabackend.api.input.CategoriaInput;
import br.com.bibliotecabackend.api.mapper.CategoriaMapper;
import br.com.bibliotecabackend.model.Categoria;
import br.com.bibliotecabackend.service.CategoriaService;

@CrossOrigin("*")
@RestController
@RequestMapping(value = "/categorias")
public class CategoriaController {
	
	@Autowired
	
	private CategoriaService categoriaService;
	
	@Autowired
	private CategoriaMapper categoriaMapper;
	
	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public CategoriaDTO salvarCategoria(@Valid @RequestBody CategoriaInput categoriaInput) {
		
		Categoria categoriaEntidade = this.categoriaMapper.toEntidade(categoriaInput);
		
		Categoria categoriaSalva = this.categoriaService.salvar(categoriaEntidade);
		
		return this.categoriaMapper.tocategoriaDTO(categoriaSalva);
	}
	
	@GetMapping
	@ResponseStatus(HttpStatus.OK)
	@CacheEvict(value = "cache-categoria", allEntries = true)
	@CachePut(value = "cache-categoria")
	public List<CategoriaDTO> getListaCategoria(
			@PageableDefault(size = 5, direction = Direction.ASC, page = 0, sort = {"nome"}) Pageable pageable){
		
		Page<Categoria> pageCategoria = categoriaService.listar(pageable);
		
		List<CategoriaDTO> listaCategoria = categoriaMapper.toList(pageCategoria);
		
		return listaCategoria;
	}
	
}
