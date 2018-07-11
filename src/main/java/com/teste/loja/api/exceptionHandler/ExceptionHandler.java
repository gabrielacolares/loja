package com.teste.loja.api.exceptionHandler;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class ExceptionHandler extends ResponseEntityExceptionHandler {
	@Autowired
	MessageSource messageSource;
	
	@Override
	protected ResponseEntity<Object> handleHttpMessageNotReadable(HttpMessageNotReadableException ex,
			HttpHeaders headers, HttpStatus status, WebRequest request) {
		String msgUsuario = messageSource.getMessage("msg.invalida", null, LocaleContextHolder.getLocale());
		String msgDev = ex.getCause().toString();
		// TODO Auto-generated method stub
		return handleExceptionInternal(ex, new Erro(msgUsuario, msgDev), headers, HttpStatus.BAD_REQUEST, request);
	}
	

	public static class Erro {
		private String msgUsuario;
		private String msgDev;
		public Erro(String msgUsuario, String msgDev) {
			super();
			this.msgUsuario = msgUsuario;
			this.msgDev = msgDev;
		}
		/**
		 * @return the msgUsuario
		 */
		public String getMsgUsuario() {
			return msgUsuario;
		}
		/**
		 * @param msgUsuario the msgUsuario to set
		 */
		public void setMsgUsuario(String msgUsuario) {
			this.msgUsuario = msgUsuario;
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
