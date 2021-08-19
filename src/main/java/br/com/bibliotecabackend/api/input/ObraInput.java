package br.com.bibliotecabackend.api.input;

import java.util.ArrayList;
import java.util.List;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;

public class ObraInput {
	
	@NotBlank(message = "título não pode ser nulo ou vázio")
	private String titulo;
	
	@NotBlank(message = "Editora não pode ser nula ou vázia")
	private String editora;
	
	@NotBlank(message = "Link para foto não pode ser nulo ou vázio")
	private String foto;
	
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

	public String getFoto() {
		return foto;
	}

	public void setFoto(String foto) {
		this.foto = foto;
	}
	
	
	
}
