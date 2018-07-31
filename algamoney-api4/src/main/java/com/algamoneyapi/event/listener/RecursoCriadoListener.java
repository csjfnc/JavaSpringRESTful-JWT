package com.algamoneyapi.event.listener;

import java.net.URI;

import javax.servlet.http.HttpServletResponse;

import org.springframework.context.ApplicationListener;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.algamoneyapi.event.ResursoCriadoEvent;

public class RecursoCriadoListener implements ApplicationListener<ResursoCriadoEvent> {

	@Override
	public void onApplicationEvent(ResursoCriadoEvent event) {

		HttpServletResponse response = event.getResponse();
		long codigo = event.getCodigo();
		
		URI uri = ServletUriComponentsBuilder.fromCurrentRequestUri().path("/{codigo}").buildAndExpand(codigo).toUri();
		response.setHeader("Location", uri.toASCIIString());
		
	}

}
