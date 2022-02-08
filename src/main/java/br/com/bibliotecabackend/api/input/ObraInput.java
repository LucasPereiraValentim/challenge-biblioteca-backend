package br.com.bibliotecabackend.api.input;

import java.util.ArrayList;
import java.util.List;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import br.com.bibliotecabackend.api.input.validation.ValidationGroups;

public class ObraInput {
	
	@NotNull(groups = ValidationGroups.obraId.class, message = "Id da obra não pode ser nula")
	private Long id;

	@NotBlank(message = "Campo titulo não pode estar vázio")
	private String titulo;
	
	@NotBlank(message = "Campo editora não pode estar vázio")
	private String editora;
	
	@Valid
	private List<AutorInput> autores = new ArrayList<>();
	
	public List<AutorInput> getAutores() {
		return autores;
	}

	public void setAutores(List<AutorInput> autores) {
		this.autores = autores;
	}

	public String getTitulo() {
		return titulo;
	}

	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}

	public String getEditora() {
		return editora;
	}

	public void setEditora(String editora) {
		this.editora = editora;
	}
	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}
	
}
