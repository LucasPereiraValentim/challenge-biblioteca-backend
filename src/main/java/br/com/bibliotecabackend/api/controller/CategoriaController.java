package br.com.bibliotecabackend.api.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.bibliotecabackend.api.dto.CategoriaDTO;
import br.com.bibliotecabackend.api.input.CategoriaInput;
import br.com.bibliotecabackend.api.mapper.CategoriaMapper;
import br.com.bibliotecabackend.model.Categoria;
import br.com.bibliotecabackend.service.CategoriaService;

@RestController
@RequestMapping(value = "obras/{obraId}/categorias")
public class CategoriaController {
	
	@Autowired
	private CategoriaService categoriaService;
	
	@Autowired
	private CategoriaMapper categoriaMapper;
	
	@PostMapping
	public CategoriaDTO salvarCategoria(@Valid @RequestBody CategoriaInput categoriaInput, @PathVariable Long obraId) {
		
		Categoria categoriaEntidade = this.categoriaMapper.toEntidade(categoriaInput);
		
		Categoria categoriaSalva = this.categoriaService.salvar(categoriaEntidade, obraId);
		
		return this.categoriaMapper.tocategoriaDTO(categoriaSalva);
	}
	
}
