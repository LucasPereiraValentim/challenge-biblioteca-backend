package br.com.bibliotecabackend.api.input;


public class Pesquisa {
	
	private String titulo;
	
	
	public Pesquisa() {
		
	}

	public Pesquisa(String titulo) {
		this.titulo = titulo;
	}

	public String getTitulo() {
		return titulo;
	}

	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}
	
	
}
