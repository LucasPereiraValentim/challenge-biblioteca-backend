package br.com.bibliotecabackend.api.input;

import javax.validation.constraints.NotBlank;

import br.com.bibliotecabackend.model.Obra;

public class CategoriaInput {
	
	@NotBlank(message = "O nome da categoria não pode ser nulo ou ficar em branco")
	private String nome;	
	
	@NotBlank(message = "Id da obra não pode null")
	private Obra obra;
	
	public Obra getObra() {
		return obra;
	}

	public void setObra(Obra obra) {
		this.obra = obra;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}


	
	
	
	
}
