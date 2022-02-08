package br.com.bibliotecabackend.api.input;

import java.util.ArrayList;
import java.util.List;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;

public class ObraInput {

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

	
}
