package com.example.app.utils;

import com.example.app.controller.excecao.Tratamentoexcecao;

public enum EstadoCandidatura {

	ADEQUADO (1),
	INADEQUADO (2),
	TALVEZ (3),
	FINALISTA (4);
	
	int status;
	
	private EstadoCandidatura(int n) {
		status = n;
	}
	
	public int getStatus() {
		return status;
	}
	
	public static EstadoCandidatura conversaoStatus(int status) {
		
		for(EstadoCandidatura valor: EstadoCandidatura.values()) {
			if(valor.getStatus() == status) {
				return valor;
			}
		}
		throw new Tratamentoexcecao("Estado da candidatura inválido");
	}
}
