package com.example.app.configuracao;

import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;

import com.example.app.model.entities.Candidato;
import com.example.app.model.entities.CandidatoVaga;
import com.example.app.model.entities.Login;
import com.example.app.model.entities.Recrutador;
import com.example.app.model.entities.Vaga;
import com.example.app.repositories.CandidatoRepository;
<<<<<<< HEAD
=======
import com.example.app.repositories.CandidatoVagaRepository;
import com.example.app.repositories.LoginRepository;
import com.example.app.repositories.RecrutadorRepository;
>>>>>>> 6428e6f9f61db07e5b023c0688d41d6714e56f48
import com.example.app.repositories.VagaRepository;

@Configuration
//@Profile("test")
public class Config implements CommandLineRunner{
	
	@Autowired
	private CandidatoRepository cr;
	
	@Autowired
	private VagaRepository vr;
	
<<<<<<< HEAD
=======
	@Autowired
	private CandidatoVagaRepository cvr;
	
	@Autowired
	private LoginRepository lr;
	
	
>>>>>>> 6428e6f9f61db07e5b023c0688d41d6714e56f48
	@Override
	public void run(String... args) throws Exception {
		
		Candidato c1 = new Candidato(null, "Paulo", "32 9 9867-4657", "paulo@gmail.com", false, "img", "semestre", "curso", "Terminio", "Anhanguera");
		Candidato c2 = new Candidato(null, "Felipe", "45 9 9097-4755", "felipe@gmail.com", false, "img", "semestre", "curso", "Terminio", "Unip");
		Candidato c4 = new Candidato(null, "Felix", "47999999999", "felix@gmail.com", false, "img", "semestre", "curso", "meio", "Estacio");
		
<<<<<<< HEAD
		cr.saveAll(Arrays.asList(c1,c2,c3));
=======
		cp.saveAll(Arrays.asList(c1,c2,c4));
>>>>>>> 6428e6f9f61db07e5b023c0688d41d6714e56f48
		
	
		Vaga v1 = new Vaga(null, "Programador", "Desenvolver programas na linguagem C#", "Conhecimento em C# e Banco de dados", "Bahia", "Home office");
<<<<<<< HEAD
		Vaga v2 = new Vaga(null, "DBA", "Administrar bancos de dados com SQL", "Conhecimento em SQL e MySQL", "São Paulo", "Presencial");
		vr.saveAll(Arrays.asList(v1, v2));
=======
		Vaga v2 = new Vaga(null, "Esteticista", "#### ", "Conhecimento em ....", "Bahia", "Home office");
		
		vr.saveAll(Arrays.asList(v1,v2));
>>>>>>> 6428e6f9f61db07e5b023c0688d41d6714e56f48
		
		c1.addVaga(v1);
		
		CandidatoVaga cv = new CandidatoVaga(c1, v2);
		v2.setCandidaturas(c1);
		
		CandidatoVaga cv1 = new CandidatoVaga(c2, v1);
		v1.setCandidaturas(c2);
		cvr.saveAll(Arrays.asList(cv,cv1));
		
		Login l1 = new Login(null, "julia@gmail.com", "Julia", "dshgyg45");
		Login l2 = new Login(null, "Fernanda@gmail.com", "Fe", "fhh478");
		
		lr.saveAll(Arrays.asList(l1,l2));
		
		c2.addVaga(v1);
	}
}
