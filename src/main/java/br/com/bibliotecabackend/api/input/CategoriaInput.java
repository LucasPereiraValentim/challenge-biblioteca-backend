package br.com.bibliotecabackend.api.input;

import javax.validation.constraints.NotBlank;

public class CategoriaInput {
	
	@NotBlank(message = "O nome da categoria não pode ser nulo ou ficar em branco")
	private String nome;	
	
	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}


	
	
	
	
}
