package br.com.bibliotecabackend.api.input;

import javax.validation.constraints.NotBlank;

public class AutorInput {
	
	@NotBlank
	private String nome;

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}
	
	
}
