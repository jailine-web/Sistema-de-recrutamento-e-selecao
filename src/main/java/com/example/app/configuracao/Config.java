package com.example.app.configuracao;

import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;

import com.example.app.model.entities.Candidato;
import com.example.app.model.entities.Vaga;
import com.example.app.repositories.CandidatoRepository;
import com.example.app.repositories.VagaRepository;

@Configuration
public class Config implements CommandLineRunner{
	
	@Autowired
	private CandidatoRepository cr;
	
	@Autowired
	private VagaRepository vr;
	
	@Override
	public void run(String... args) throws Exception {
		
		Candidato c1 = new Candidato(null, "Mavie", "32998674657", "mavie@gmail.com", false, "img", "semestre", "curso", "termino", "Anhanguera");
		Candidato c2 = new Candidato(null, "Felipe", "45990974755", "fevie@gmail.com", false, "img", "semestre", "curso", "termino", "Unip");
		Candidato c3 = new Candidato(null, "Felix", "47999999999", "felix@gmail.com", false, "img", "semestre", "curso", "meio", "Estacio");
		
		cr.saveAll(Arrays.asList(c1,c2,c3));
		
		Vaga v1 = new Vaga(null, "Programador", "Desenvolver programas na linguagem C#", "Conhecimento em C# e Banco de dados", "Bahia", "Home office");
		Vaga v2 = new Vaga(null, "DBA", "Administrar bancos de dados com SQL", "Conhecimento em SQL e MySQL", "São Paulo", "Presencial");
		vr.saveAll(Arrays.asList(v1, v2));
		
		c1.addVaga(v1);
		c2.addVaga(v1);
	}
}
