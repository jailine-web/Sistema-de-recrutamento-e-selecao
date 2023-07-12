package com.example.app.controller.excecao;

import java.util.NoSuchElementException;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import jakarta.servlet.http.HttpServletRequest;

@ControllerAdvice
public class ManipuladorDeExcecao {

	@ExceptionHandler(NoSuchElementException.class)
	public ResponseEntity<ErroPadrao> TratamentoDeexcecao(NoSuchElementException e, HttpServletRequest hs){
		
		HttpStatus estado = HttpStatus.NOT_FOUND;
		
		ErroPadrao erro = new ErroPadrao(System.currentTimeMillis(), estado.value(), e.getMessage(), "Usuário não encontrado", hs.getRequestURI());
		return ResponseEntity.status(estado).body(erro);
	}
}
