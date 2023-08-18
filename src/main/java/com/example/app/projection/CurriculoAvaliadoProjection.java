package com.example.app.projection;

import com.example.app.utils.StatusCurriculoAvaliado;

public interface CurriculoAvaliadoProjection {
	Integer getId();
	String getNome();
	StatusCurriculoAvaliado getCurriculoAvaliado();
}
