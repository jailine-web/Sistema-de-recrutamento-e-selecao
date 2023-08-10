package com.example.app.services;

import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Service;

import com.example.app.model.entities.Mensagem;

@Service
public class MensagemProducer {
	
	private final RabbitTemplate rabbitTemplate;
	
	public MensagemProducer(RabbitTemplate rabbitTemplate) {
		this.rabbitTemplate = rabbitTemplate;
	}
	
	public void enviarMensagem(Mensagem mensagem) {
		rabbitTemplate.convertAndSend("mensagens-exchange", "mensagens-queue", mensagem);
	}
}
