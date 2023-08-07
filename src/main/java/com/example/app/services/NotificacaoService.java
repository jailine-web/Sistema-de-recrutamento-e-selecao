package com.example.app.services;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import com.example.app.model.entities.Alerta;
import com.example.app.model.entities.Candidatura;
import com.example.app.model.entities.Mensagem;
import com.example.app.repositories.AlertaRepository;
import com.example.app.repositories.CandidaturaRepository;
import com.example.app.repositories.MensagemRepository;
import com.example.app.utils.EstadoInscricao;

import jakarta.transaction.Transactional;

@Service
public class NotificacaoService {
	
	@Autowired
	private CandidaturaRepository candidaturaRepository;
	
	@Autowired
	private AlertaRepository alertaRepository;
	
	@Autowired 
	private MensagemRepository mensagemRepository;
	
	public NotificacaoService() {
	}
	
	@Transactional
	public void gerarAlertas(List<Candidatura> candidaturas) {
		for (Candidatura candidatura : candidaturas) {
			EstadoInscricao estado = candidatura.getEstado();
			
			if (estado == EstadoInscricao.NOVA_VAGA) {
				emitirAlerta(candidatura.getId(),"Nova vaga disponível para candidato");
				candidatura.setEstado(EstadoInscricao.ALERTA_GERADO);
				
			} else if (estado == EstadoInscricao.SELECIONADO) {
				emitirAlerta(candidatura.getId(),"Você foi selecionado para a próxima etapa");
				candidatura.setEstado(EstadoInscricao.ALERTA_GERADO);
				
			} else if (estado == EstadoInscricao.REJEITADO) {
				emitirAlerta(candidatura.getId(),"Pedimos desculpas mas você não passou para a próxima etapa");
				candidatura.setEstado(EstadoInscricao.ALERTA_GERADO);
				
			} else if (estado == EstadoInscricao.AGUARDANDO_ENTREVISTA) {
				emitirAlerta(candidatura.getId(),"Você está aguardando para a realização de uma entrevista");
				candidatura.setEstado(EstadoInscricao.ALERTA_GERADO);
				
			} else if (estado == EstadoInscricao.ENTREVISTA_REALIZADA) {
				emitirAlerta(candidatura.getId(),"Sua entrevista foi realizada, aguarda os próximos passo");
				candidatura.setEstado(EstadoInscricao.ALERTA_GERADO);
			}

		}
		candidaturaRepository.saveAll(candidaturas);
	}
	
	private void emitirAlerta(Long candidaturaId, String mensagem) {
		Alerta alerta = new Alerta();
		alerta.setCandidaturaId(candidaturaId);
		alerta.setMensagem(mensagem);
		alertaRepository.save(alerta);
		
		Mensagem mensagemParaCandidato = new Mensagem();
		mensagemParaCandidato.setCandidatoId(candidaturaId);
		mensagemParaCandidato.setConteudo(mensagem);
		mensagemParaCandidato.setDataEnvio(LocalDateTime.now());
		mensagemRepository.save(mensagemParaCandidato);
	}
	
	public void enviarMensagensPendentes() {
		List<Mensagem> mensagensPendentes = mensagemRepository.findAll();
		
		for (Mensagem mensagem : mensagensPendentes) {
			Candidatura candidatura = candidaturaRepository.findById(mensagem.getCandidatoId()).orElse(null);
			if (candidatura != null && candidatura.getCandidato() != null) {
				String destinatario = candidatura.getCandidato().getNome();
				String conteudo = mensagem.getConteudo();
				
				System.out.println("Enviando mensagem para " + destinatario + ": " + conteudo);
				
				mensagem.setDataEnvio(LocalDateTime.now());
				mensagemRepository.save(mensagem);
			}
		}
	}
	
	@Scheduled(fixedDelay = 300000) // Agendando o envio a cada 5 minutos (3000000 milisegundos)
	public void agendarEnvioMensagensPendentes() {
		enviarMensagensPendentes();
	}
}
