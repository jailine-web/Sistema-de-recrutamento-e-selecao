package com.example.app.configuracao;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.DirectExchange;
import org.springframework.amqp.core.Exchange;
import org.springframework.amqp.core.Queue;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class MensageriaConfig {

    @Bean
    Exchange mensagensExchange() {
    	return new DirectExchange("mensagens-exchange");
	}
    
    @Bean
    Queue mensagensQueue() {
    	return new Queue("mensagens-queue");
    }
    
    @Bean
    Binding binding(Queue mensagensQueue, Exchange mensagensExchange) {
    	return BindingBuilder.bind(mensagensQueue).to(mensagensExchange).with("mensagens-queue").noargs();
    }
}
