package br.com.bibliotecabackend.exception;

public class TituloException extends RuntimeException{
	
	private static final long serialVersionUID = 1L;
	
	public TituloException(String erro) {
		super(erro);
	}
	
}
