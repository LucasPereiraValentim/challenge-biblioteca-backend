package br.com.bibliotecabackend.api.mapper;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.stereotype.Component;

import br.com.bibliotecabackend.api.dto.CategoriaDTO;
import br.com.bibliotecabackend.api.input.CategoriaInput;
import br.com.bibliotecabackend.model.Categoria;

@Component
public class CategoriaMapper {

	@Autowired
	private ModelMapper modelMapper;
	
	
	public Categoria toEntity(CategoriaInput categoriaInput) {
		return modelMapper.map(categoriaInput, Categoria.class);
	}


	public CategoriaDTO toDTO(Categoria categoria) {
		return this.modelMapper.map(categoria, CategoriaDTO.class);
	}


	public Page<CategoriaDTO> toListDTO(Page<Categoria> pageCategoria) {
		List<CategoriaDTO> listaDTO = pageCategoria.stream().map(this::toDTO).collect(Collectors.toList());
		return new PageImpl<>(listaDTO);
	}

}
