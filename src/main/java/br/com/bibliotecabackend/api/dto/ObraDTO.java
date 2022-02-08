package br.com.bibliotecabackend.api.dto;

import java.util.ArrayList;
import java.util.List;

public class ObraDTO {
	
	private Long id;
	
	private String titulo;
	
	private String editora;
	
	private List<AutorDTO> autores = new ArrayList<>();
	
	public List<AutorDTO> getAutores() {
		return autores;
	}

	public void setAutores(List<AutorDTO> autores) {
		this.autores = autores;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
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
