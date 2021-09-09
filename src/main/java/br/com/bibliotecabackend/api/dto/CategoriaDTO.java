package br.com.bibliotecabackend.api.dto;

public class CategoriaDTO {
	
	private Long id;
	
	private String nome;
	
	private ObraDTO obra;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public ObraDTO getObra() {
		return obra;
	}

	public void setObra(ObraDTO obra) {
		this.obra = obra;
	}
	
	
}
