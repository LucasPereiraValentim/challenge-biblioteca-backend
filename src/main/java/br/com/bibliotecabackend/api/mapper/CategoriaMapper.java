package br.com.bibliotecabackend.api.mapper;

import java.util.ArrayList;
import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Component;

import br.com.bibliotecabackend.api.dto.CategoriaDTO;
import br.com.bibliotecabackend.api.input.CategoriaInput;
import br.com.bibliotecabackend.model.Categoria;

@Component
public class CategoriaMapper {

	@Autowired
	private ModelMapper modelMapper;
	
	
	public Categoria toEntidade(CategoriaInput categoriaInput) {
		return modelMapper.map(categoriaInput, Categoria.class);
	}


	public CategoriaDTO tocategoriaDTO(Categoria categoria) {
		return this.modelMapper.map(categoria, CategoriaDTO.class);
	}


	public List<CategoriaDTO> toList(Page<Categoria> pageCategoria) {
		
		List<Categoria> listaEntidade = pageCategoria.getContent();
		
		List<CategoriaDTO> listaDTO = new ArrayList<>();
		
		for (Categoria categoria : listaEntidade) {
			listaDTO.add(this.tocategoriaDTO(categoria));
		}
		
		return listaDTO;
	}

}
