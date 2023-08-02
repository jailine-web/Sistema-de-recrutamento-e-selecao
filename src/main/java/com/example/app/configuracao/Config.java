package com.example.app.configuracao;

import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

import com.example.app.model.entities.Candidato;
import com.example.app.model.entities.Login;
import com.example.app.model.entities.Vaga;
import com.example.app.repositories.CandidatoRepository;
import com.example.app.repositories.LoginRepository;
import com.example.app.repositories.VagaRepository;

@Configuration
@Profile("test")
public class Config implements CommandLineRunner{
	
	@Autowired
	private CandidatoRepository cr;
	
	@Autowired
	private VagaRepository vr;
	
	@Autowired
	private LoginRepository lr;
	
	@Override
	public void run(String... args) throws Exception {
		
<<<<<<< HEAD
		Candidato c1 = new Candidato(null, "Paulo", "32 9 9867-4657", "paulo@gmail.com", false, "img", "semestre", "curso", "Terminio", "Anhanguera");
		Candidato c2 = new Candidato(null, "Felipe", "45 9 9097-4755", "felipe@gmail.com", false, "img", "semestre", "curso", "Terminio", "Unip");
=======
		Candidato c1 = new Candidato(null, "Paulo", "32998674657", "paulo@gmail.com", false, "img", "semestre", "curso", "Terminio", "Anhanguera");
		Candidato c2 = new Candidato(null, "Felipe", "45990974755", "felipe@gmail.com", false, "img", "semestre", "curso", "Terminio", "Unip");
>>>>>>> cb3a34f71a6f26d9e848dac9de94bae1f386f173
		Candidato c3 = new Candidato(null, "Felix", "47999999999", "felix@gmail.com", false, "img", "semestre", "curso", "meio", "Estacio");
		

		cr.saveAll(Arrays.asList(c1,c2,c3));

		
	
		Vaga v1 = new Vaga(null, "Programador", "Desenvolver programas na linguagem C#", "Conhecimento em C# e Banco de dados", "Bahia", "Home office");
		Vaga v2 = new Vaga(null, "Esteticista", "#### ", "Conhecimento em ....", "Bahia", "Home office");
		
		vr.saveAll(Arrays.asList(v1,v2));
		
		c1.addVaga(v1);
		
		
		Login l1 = new Login(null, "julia@gmail.com", "Julia", "dshgyg45");
		Login l2 = new Login(null, "Fernanda@gmail.com", "Fe", "fhh478");
		
		lr.saveAll(Arrays.asList(l1,l2));
		
		c2.addVaga(v1);
	}
<<<<<<< HEAD
}
=======
}
>>>>>>> cb3a34f71a6f26d9e848dac9de94bae1f386f173
