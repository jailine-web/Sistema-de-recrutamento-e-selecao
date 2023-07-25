package com.example.app.utils;

import com.example.app.excecao.Tratamentoexcecao;

public enum EstadoVaga {

	RECEBIDO (1),
	AVALIADO (2),
	PRE_SELECIONADO (3),
	FINALISTA (4);
	
	int status;
	
	private EstadoVaga(int n) {
		status = n;
	}
	
	public int getStatus() {
		return status;
	}
	
	public static EstadoVaga conversaoStatus(int status) {
		
		for(EstadoVaga valor: EstadoVaga.values()) {
			if(valor.getStatus() == status) {
				return valor;
			}
		}
		throw new Tratamentoexcecao("Estado da candidatura inválido");
	}
}
