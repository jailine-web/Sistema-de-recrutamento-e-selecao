package com.example.app.configuracao;

import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;

import com.example.app.model.entities.Candidato;
import com.example.app.model.entities.CandidatoVaga;
import com.example.app.model.entities.Login;
import com.example.app.model.entities.Vaga;
import com.example.app.repositories.CandidatoRepository;
import com.example.app.repositories.CandidatoVagaRepository;
import com.example.app.repositories.LoginRepository;
import com.example.app.repositories.VagaRepository;

@Configuration
//@Profile("test")
public class Config implements CommandLineRunner{
	
	@Autowired
	private CandidatoRepository cr;
	
	@Autowired
	private CandidatoVagaRepository cvr;
	
	@Autowired
	private VagaRepository vr;
	
	@Autowired
	private LoginRepository lr;
	
	@Override
	public void run(String... args) throws Exception {
		
		Candidato c1 = new Candidato(null, "Paulo", "32 9 9867-4657", "paulo@gmail.com", false, "img", "semestre", "curso", "Terminio", "Anhanguera");
		Candidato c2 = new Candidato(null, "Felipe", "45 9 9097-4755", "felipe@gmail.com", false, "img", "semestre", "curso", "Terminio", "Unip");
		Candidato c3 = new Candidato(null, "Felix", "47999999999", "felix@gmail.com", false, "img", "semestre", "curso", "meio", "Estacio");
		

		cr.saveAll(Arrays.asList(c1,c2,c3));
	
		Vaga v1 = new Vaga(null, "Programador", "Desenvolver programas na linguagem C#", "Conhecimento em C# e Banco de dados", "Bahia", "Home office");
		Vaga v2 = new Vaga(null, "Desenvolvedor Jr", "Desenvolver aplicativos web", "Conhecimento em java e Spring boot", "São Paulo", "Home office");
		Vaga v3 = new Vaga(null, "Redator Jr", " Redigir textos para publis", "Conhecimento em Linguagens", "São Paulo", "Home office");
		Vaga v4 = new Vaga(null, "Designer Jr", " Protótipos de telas", "Conhecimento em PS e adobe", "São Paulo", "presencial");
		
		vr.saveAll(Arrays.asList(v1,v2, v3, v4));
		
		c1.addVaga(v1);
		
		Login l1 = new Login(null, "julia@gmail.com", "Julia", "dshgyg45");
		Login l2 = new Login(null, "Fernanda@gmail.com", "Fe", "fhh478");
		
		lr.saveAll(Arrays.asList(l1,l2));
		
		CandidatoVaga cv1 = new CandidatoVaga(c1, v1);
		CandidatoVaga cv2 = new CandidatoVaga(c2, v2);
		CandidatoVaga cv3 = new CandidatoVaga(c1, v2);
		CandidatoVaga cv4 = new CandidatoVaga(c1, v3);
//		CandidatoVaga cv5 = new CandidatoVaga(c2, v1);
		
		cvr.saveAll(Arrays.asList(cv1,cv2, cv3, cv4));
		
		c2.addVaga(v1);
		
	}
}
