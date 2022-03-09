package br.com.bibliotecabackend.api.mapper;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.stereotype.Component;

import br.com.bibliotecabackend.api.dto.ObraDTO;
import br.com.bibliotecabackend.api.input.ObraInput;
import br.com.bibliotecabackend.model.Obra;

@Component
public class ObraMapper {

	@Autowired
	private ModelMapper modelMapper;

	public ObraDTO toObraDTO(Obra obra) {
		return modelMapper.map(obra, ObraDTO.class);
	}

	public Obra toEntidade(ObraInput obraInput) {
		return modelMapper.map(obraInput, Obra.class);
	}

	public Page<ObraDTO> toListDTO(Page<Obra> obras) {

		List<ObraDTO> listaDTO = obras.stream().map(this::toObraDTO).collect(Collectors.toList());

		return new PageImpl<>(listaDTO);

	}

}
