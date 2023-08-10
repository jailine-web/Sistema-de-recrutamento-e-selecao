package com.example.app.services;

import java.time.LocalDateTime;

import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.app.model.entities.Candidato;
import com.example.app.model.entities.Mensagem;
import com.example.app.repositories.CandidatoRepository;
import com.example.app.repositories.MensagemRepository;

@Service
public class MensagemConsumer {
	
	@Autowired
	private NotificacaoService notificacaoService;
	
	@Autowired 
	private MensagemRepository mensagemRepository;
	
	@Autowired
	private CandidatoRepository candidatoRepository;
	
	@RabbitListener(queues="mensagens-queue")
	public void receberMensagem(Mensagem mensagem) {
		Candidato candidato = buscarCandidatoPorId(mensagem.getCandidatoId());
		if (candidato != null) {
			notificacaoService.enviarNotificacao(candidato, mensagem.getConteudo());
		}
		
		mensagem.setDataEnvio(LocalDateTime.now());
		mensagemRepository.save(mensagem);
	}
	
	private Candidato buscarCandidatoPorId(Integer candidatoId) {
		return candidatoRepository.findById(candidatoId).orElse(null);
	}
}
