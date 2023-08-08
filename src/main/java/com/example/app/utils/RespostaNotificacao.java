package com.example.app.utils;

import java.util.List;

import com.example.app.model.entities.Alerta;

public class RespostaNotificacao {
	private List<Alerta> alertas;
	private String status;
	
	public RespostaNotificacao() {
	}

	public List<Alerta> getAlertas() {
		return alertas;
	}

	public void setAlertas(List<Alerta> alertas) {
		this.alertas = alertas;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}
	
	
}
