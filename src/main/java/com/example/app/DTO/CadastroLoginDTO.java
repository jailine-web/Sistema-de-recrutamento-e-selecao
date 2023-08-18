package com.example.app.DTO;

import com.example.app.utils.Regras;

public record CadastroLoginDTO(String email,String usuario, String senha, Regras regras) {

}
