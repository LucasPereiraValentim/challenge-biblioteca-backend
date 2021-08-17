package br.com.bibliotecabackend.exception;

public class ObraException extends RuntimeException{
	
	private static final long serialVersionUID = 1L;
	
	public ObraException(String erro) {
		super(erro);
	}
	
}
