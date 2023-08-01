package com.example.app.controller.excecao;

import java.util.NoSuchElementException;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.ConstraintViolationException;

@ControllerAdvice
public class ManipuladorDeExcecao {

	@ExceptionHandler(NoSuchElementException.class)
	public ResponseEntity<ErroPadrao> TratamentoDeexcecao(NoSuchElementException e, HttpServletRequest hs){
		
		HttpStatus estado = HttpStatus.NOT_FOUND;
		
		ErroPadrao erro = new ErroPadrao(System.currentTimeMillis(), estado.value(), e.getMessage(), "Usuário não encontrado", hs.getRequestURI());
		return ResponseEntity.status(estado).body(erro);
	}
	
	@ExceptionHandler(ConstraintViolationException.class)
	public ResponseEntity<ErroPadrao> TratamentoDeExcecao(ConstraintViolationException e, HttpServletRequest requisicao){
		
		HttpStatus status = HttpStatus.BAD_REQUEST;
		
		ErroPadrao er = new ErroPadrao(System.currentTimeMillis(), status.value(), e.getMessage(), "Os campos de login e senha não podem ser vazios", requisicao.getRequestURI());
		return ResponseEntity.status(status).body(er);
	}
}
