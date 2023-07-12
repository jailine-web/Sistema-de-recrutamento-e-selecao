package com.example.app.controller.excecao;

import org.springframework.boot.context.config.ConfigDataResourceNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import jakarta.servlet.http.HttpServletRequest;

@ControllerAdvice
public class RecursoNaoEncontrado {

	@ExceptionHandler(ConfigDataResourceNotFoundException.class)  
	public ResponseEntity<ErroPadrao> recursoNaoEncontrado(ConfigDataResourceNotFoundException e, HttpServletRequest request){
		String erro  = "Recurso não encontrado";
		HttpStatus status = HttpStatus.NOT_FOUND; 
		ErroPadrao err = new ErroPadrao(System.currentTimeMillis(), status.value(), erro, e.getMessage(), request.getRequestURI());
				
		return ResponseEntity.status(status).body(err);
		
	}
}
