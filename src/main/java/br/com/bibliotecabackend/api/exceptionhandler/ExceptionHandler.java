package br.com.bibliotecabackend.api.exceptionhandler; 

import java.time.OffsetDateTime;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import br.com.bibliotecabackend.exception.RecursoNaoEncontrado;
import br.com.bibliotecabackend.exception.TituloException;

@ControllerAdvice
public class ExceptionHandler extends ResponseEntityExceptionHandler{
	
	@Autowired
	private MessageSource messageSource;
	
	
	@Override
	protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex,
			HttpHeaders headers, HttpStatus status, WebRequest request) {
			
		List<Campo> camposlista = new ArrayList<>();
		
		for (ObjectError error : ex.getBindingResult().getAllErrors()) {
			String nome = ((FieldError) error).getField();
			
			String mensagem = messageSource.getMessage(error, LocaleContextHolder.getLocale());
			
			camposlista.add(new Campo(nome, mensagem));
		}
		
		
		Erro erro = new Erro();
		erro.setStatus(status.value());
		erro.setDataHora(OffsetDateTime.now());
		erro.setTitulo("Um ou mais campos estão nulos ou vázios");
		erro.setCampos(camposlista);
		
		return super.handleExceptionInternal(ex, erro, headers, status, request);
	}
	
	@org.springframework.web.bind.annotation.ExceptionHandler(TituloException.class)
	protected ResponseEntity<Object> handlerTitulo(TituloException e, WebRequest request){
		
		HttpStatus status = HttpStatus.BAD_REQUEST;
		
		Erro erro = new Erro();
		erro.setStatus(status.value());
		erro.setDataHora(OffsetDateTime.now());
		erro.setTitulo(e.getMessage());
		
		
		return handleExceptionInternal(e, erro, new HttpHeaders(), status, request);
	}
	
	@org.springframework.web.bind.annotation.ExceptionHandler(RecursoNaoEncontrado.class)
	protected ResponseEntity<Object> hanlerRecursoNaoEncontrado(RecursoNaoEncontrado e, WebRequest request){
		HttpStatus status = HttpStatus.NOT_FOUND;
		
		Erro erro = new Erro();
		erro.setStatus(status.value());
		erro.setDataHora(OffsetDateTime.now());
		erro.setTitulo(e.getMessage());
		
		return handleExceptionInternal(e, erro, new HttpHeaders(), status, request);
	}
	
}
