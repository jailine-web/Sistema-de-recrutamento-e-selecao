package com.example.app.configuracao;

import java.time.LocalDateTime;
import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;

import com.example.app.model.entities.Candidato;
import com.example.app.model.entities.CandidatoVaga;
import com.example.app.model.entities.Candidatura;
import com.example.app.model.entities.Vaga;
import com.example.app.repositories.AlertaRepository;
import com.example.app.repositories.CandidatoRepository;
import com.example.app.repositories.CandidatoVagarepository;
import com.example.app.repositories.CandidaturaRepository;
import com.example.app.repositories.VagaRepository;
import com.example.app.utils.EstadoInscricao;
import com.example.app.utils.StatusCurriculoAvaliado;

@Configuration
//@Profile("test")
public class Config implements CommandLineRunner{
	
	@Autowired
	private CandidatoRepository cr;
	
	@Autowired
	private VagaRepository vr;
	
	@Autowired
	private CandidaturaRepository cd;
	
	@Autowired
	private CandidatoVagarepository cv;
	
	@Override
	public void run(String... args) throws Exception {
		
		Candidato c1 = new Candidato(null, "Paulo", "32 9 9867-4657", "paulo@gmail.com", false, "img", "semestre", "curso", "Terminio", "Anhanguera","São Paulo");
		c1.setCurriculoAvaliado(StatusCurriculoAvaliado.AVALIADO);
		Candidato c2 = new Candidato(null, "Felipe", "45 9 9097-4755", "felipe@gmail.com", false, "img", "semestre", "curso", "Terminio", "Unip", "Bahia");
		Candidato c3 = new Candidato(null, "Felix", "47999999999", "felix@gmail.com", false, "img", "semestre", "curso", "meio", "Estacio","SC");

		cr.saveAll(Arrays.asList(c1,c2,c3));
	
		Vaga v1 = new Vaga(null, "Programador", "Desenvolver programas na linguagem C#", "Conhecimento em C# e Banco de dados", "Bahia", "Home office",  "TI");
		Vaga v2 = new Vaga(null, "Desenvolvedor Jr", "Desenvolver aplicativos web", "Conhecimento em java e Spring boot", "São Paulo", "Home office", "TI");
		Vaga v3 = new Vaga(null, "Redator Jr", " Redigir textos para publis", "Conhecimento em Linguagens", "São Paulo", "Home office", "Redator");
		Vaga v4 = new Vaga(null, "Designer Jr", " Protótipos de telas", "Conhecimento em PS e adobe", "São Paulo", "presencial", "Design");
		
		vr.saveAll(Arrays.asList(v1,v2, v3, v4));
		
		c1.addVaga(v1);
		
		Candidatura cd1 = new Candidatura(null, v1, c1, EstadoInscricao.SELECIONADO,LocalDateTime.now());
		Candidatura cd2 = new Candidatura(null, v2, c2, EstadoInscricao.AGUARDANDO_ENTREVISTA,LocalDateTime.now());
		Candidatura cd3 = new Candidatura(null, v4, c2, EstadoInscricao.INADEQUADO, LocalDateTime.now());
		Candidatura cd4 = new Candidatura(null, v1, c3, EstadoInscricao.TALVEZ, LocalDateTime.now());
		Candidatura cd5 = new Candidatura(null, v4, c2, null, null);
		
		cd.saveAll(Arrays.asList(cd1,cd2,cd3,cd4,cd5));
		
		CandidatoVaga cv1 = new CandidatoVaga(c1, v4);
		CandidatoVaga cv2 = new CandidatoVaga(c1, v3);
		CandidatoVaga cv3 = new CandidatoVaga(c2, v1);
		
		cv.saveAll(Arrays.asList(cv1, cv2, cv3));
		
	}
}
