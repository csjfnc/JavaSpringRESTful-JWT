package com.algamoneyapi.exceptionhandler;

import java.util.ArrayList;
import java.util.List; 

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class AlgaExceptionHanlder extends ResponseEntityExceptionHandler {

	@Autowired
	private MessageSource messageSource;
	
	//Requisição con atirbutes que nao existe no objeto	
	@Override
	protected ResponseEntity<Object> handleHttpMessageNotReadable(HttpMessageNotReadableException ex,
			HttpHeaders headers, HttpStatus status, WebRequest request) {
		// TODO Auto-generated method stub
		
		String mensagemUsuario = messageSource.getMessage("mensagem.invalida", null, LocaleContextHolder.getLocale());
		String mensagemDeveloper = ex.getCause() != null ? ex.getCause().toString() : ex.toString();  
		
		return handleExceptionInternal(ex, new Erros(mensagemUsuario, mensagemDeveloper), headers, HttpStatus.BAD_REQUEST, request);
	}
	
	@ExceptionHandler({EmptyResultDataAccessException.class})
	public ResponseEntity<Object> handleEmptyResultDataAccessException(EmptyResultDataAccessException ex, WebRequest request){
		String menagemUsuario = messageSource.getMessage("recurso.nao-encontrado", null, LocaleContextHolder.getLocale());
		String mensagemDeveloper = ex.getCause() != null ? ex.getCause().toString() : ex.toString();
		
		return handleExceptionInternal(ex, new Erros(menagemUsuario, mensagemDeveloper), new HttpHeaders(), HttpStatus.BAD_REQUEST, request);
	}
	
	@Override
	protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex,
			HttpHeaders headers, HttpStatus status, WebRequest request) {
		List<Erros> erros = criarListadeErros(ex.getBindingResult());	
		return handleExceptionInternal(ex, erros, headers, status, request);
	}
	
	public List<Erros> criarListadeErros(BindingResult bindingResult){
		List<Erros> erros = new ArrayList<>();
		for(FieldError error : bindingResult.getFieldErrors()){
			String mensagemUsuario = messageSource.getMessage(error, LocaleContextHolder.getLocale());
			String mensagemDesenvolvedor = error.toString();
			erros.add(new Erros(mensagemUsuario, mensagemDesenvolvedor));
		}
		
		return erros;
	}
	
	
	public class Erros{
		private String erroUsuario;
		private String erroDesenvolvedor;
		
		public Erros(String erroUsuario, String erroDesenvolvedor){
			this.erroUsuario = erroUsuario;
			this.erroDesenvolvedor = erroDesenvolvedor;
		}

		public String getErroUsuario() {
			return erroUsuario;
		}

		public String getErroDesenvolvedor() {
			return erroDesenvolvedor;
		}		
	}
}
