package br.com.bibliotecabackend.api.input;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.groups.ConvertGroup;

import br.com.bibliotecabackend.api.input.validation.ValidationGroups;

public class CategoriaInput {
	
	@NotBlank(message = "O nome da categoria não pode ser nulo ou ficar em branco")
	private String nome;
	
	@Valid
	@ConvertGroup(to = ValidationGroups.obraId.class)
	private ObraInput obra;
	
	public ObraInput getObra() {
		return obra;
	}

	public void setObra(ObraInput obra) {
		this.obra = obra;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}


	
	
	
	
}
