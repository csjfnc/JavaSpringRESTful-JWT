package com.algamoneyapi.event;

import javax.servlet.http.HttpServletResponse;

import org.springframework.context.ApplicationEvent;

public class ResursoCriadoEvent extends ApplicationEvent {

	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private HttpServletResponse response;
	private long codigo;
	
	
	public ResursoCriadoEvent(Object source, HttpServletResponse response, long codigo) {
		super(source);

		this.response = response;
		this.codigo = codigo;
	}


	public HttpServletResponse getResponse() {
		return response;
	}


	public void setResponse(HttpServletResponse response) {
		this.response = response;
	}


	public long getCodigo() {
		return codigo;
	}


	public void setCodigo(long codigo) {
		this.codigo = codigo;
	}
}
