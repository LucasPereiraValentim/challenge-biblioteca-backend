package br.com.bibliotecabackend.api.exceptionhandler;

public class Campo {
	
	public Campo(String nome, String mensagem) {
		super();
		this.nome = nome;
		this.mensagem = mensagem;
	}
	
	private String nome;
	
	private String mensagem;

	public String getMensagem() {
		return mensagem;
	}
	
	public String getNome() {
		return nome;
	}
}

