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
import com.example.app.repositories.CandidatoVagaRepository;
import com.example.app.repositories.LoginRepository;
import com.example.app.repositories.RecrutadorRepository;
import com.example.app.repositories.VagaRepository;

@Configuration
<<<<<<< HEAD
//@Profile("test")
=======
>>>>>>> 348dd47a5a5e8e285ff0dc19e5dd0c13a5b7af82
public class Config implements CommandLineRunner{
	
	@Autowired
	private CandidatoRepository cp;
	
	@Autowired
	private RecrutadorRepository rr;
	
	@Autowired
	private VagaRepository vr;
	
	@Autowired
	private CandidatoVagaRepository cvr;
	
	@Autowired
	private LoginRepository lr;
	
	
	@Override
	public void run(String... args) throws Exception {
		
<<<<<<< HEAD
		Candidato c1 = new Candidato(null, "Paulo", "32 9 9867-4657", "paulo@gmail.com", false, "img", "semestre", "curso", "Terminio", "Anhanguera");
		Candidato c2 = new Candidato(null, "Felipe", "45 9 9097-4755", "felipe@gmail.com", false, "img", "semestre", "curso", "Terminio", "Unip");
=======
		Candidato c1 = new Candidato(null, "Mavie", "32998674657", "mavie@gmail.com", false, "img", "semestre", "curso", "termino", "Anhanguera");
		Candidato c2 = new Candidato(null, "Felipe", "45990974755", "fevie@gmail.com", false, "img", "semestre", "curso", "termino", "Unip");
		Candidato c3 = new Candidato(null, "Felix", "47999999999", "felix@gmail.com", false, "img", "semestre", "curso", "meio", "Estacio");
>>>>>>> 348dd47a5a5e8e285ff0dc19e5dd0c13a5b7af82
		
		cp.saveAll(Arrays.asList(c1,c2,c3));
		
		Recrutador r1 = new Recrutador(null, "Jão", "jao@gmail.com", "João", "123456", false, "curriculo", "img", "RH");
		
		rr.save(r1);
		
		Vaga v1 = new Vaga(null, "Programador", "Desenvolver programas na linguagem C#", "Conhecimento em C# e Banco de dados", "Bahia", "Home office");
		Vaga v2 = new Vaga(null, "Esteticista", "#### ", "Conhecimento em ....", "Bahia", "Home office");
		
		vr.saveAll(Arrays.asList(v1,v2));
		
		c1.addVaga(v1);
<<<<<<< HEAD
		
		CandidatoVaga cv = new CandidatoVaga(c1, v2);
		v2.setCandidaturas(c1);
		
		CandidatoVaga cv1 = new CandidatoVaga(c2, v1);
		v1.setCandidaturas(c2);
		cvr.saveAll(Arrays.asList(cv,cv1));
		
		Login l1 = new Login(null, "julia@gmail.com", "Julia", "dshgyg45");
		Login l2 = new Login(null, "Fernanda@gmail.com", "Fe", "fhh478");
		
		lr.saveAll(Arrays.asList(l1,l2));
		
=======
		c2.addVaga(v1);
>>>>>>> 348dd47a5a5e8e285ff0dc19e5dd0c13a5b7af82
	}

}
