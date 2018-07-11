package com.teste.loja.api.exceptionHandler;

import java.util.ArrayList;
import java.util.List;

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
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;


@ControllerAdvice
public class LojaExceptionHandler extends ResponseEntityExceptionHandler {
	
	@Override
	protected ResponseEntity<Object> handleHttpMessageNotReadable(HttpMessageNotReadableException ex,
			HttpHeaders headers, HttpStatus status, WebRequest request) {
		// TODO Auto-generated method stub
		
		String msgDev = ex.getCause().toString();
		return handleExceptionInternal(ex, msgDev,headers, HttpStatus.BAD_REQUEST, request);
	}
	
	@Override
	protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex,
			HttpHeaders headers, HttpStatus status, WebRequest request) {
		List<Erro> erros = addErro(ex.getBindingResult());
		return handleExceptionInternal(ex, erros, headers, HttpStatus.BAD_REQUEST, request);
	}
	
	//Método criado para tratar exceções  
	@ExceptionHandler({EmptyResultDataAccessException.class})
	@ResponseStatus(HttpStatus.NOT_FOUND)
	public void EmptyResultDataAccessException(RuntimeException ex) {
		
	}
	
	//Método criado para adicionar mensagens de exceções para uma lista de mensagens de erros
	public List<Erro> addErro(BindingResult bindingResult){
		List<Erro> erros = new ArrayList<>();
		
		for(FieldError fieldErro : bindingResult.getFieldErrors()){
			String msgErroDev = fieldErro.toString();
			erros.add(new Erro(msgErroDev));
		}
		return erros;
	}
	

	public static class Erro {
		private String msgDev;
		public Erro(String msgDev) {
			super();
			this.msgDev = msgDev;
		}
		
		/**
		 * @return the msgDev
		 */
		public String getMsgDev() {
			return msgDev;
		}
		/**
		 * @param msgDev the msgDev to set
		 */
		public void setMsgDev(String msgDev) {
			this.msgDev = msgDev;
		}
			
	}
}
