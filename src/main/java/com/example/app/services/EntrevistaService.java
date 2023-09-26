package com.example.app.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import com.example.app.controller.excecao.Tratamentoexcecao;
import com.example.app.model.entities.Entrevista;
import com.example.app.model.entities.MensagemEntrevista;
import com.example.app.repositories.EntrevistaRepository;
import com.example.app.repositories.MensagemEntrevistaRepository;

import jakarta.transaction.Transactional;

@Service
public class EntrevistaService {

	@Autowired
	private EntrevistaRepository entrevistaRepository;

	@Autowired
	private MensagemEntrevistaRepository mensagemEntrevistaRepository;

	@Transactional
	public Entrevista inserirEntrevista(Entrevista entrevista) {

		Entrevista entrevistaSalva = entrevistaRepository.save(entrevista);
		return entrevistaSalva;
	}

	@Transactional
	public Entrevista atualizarEntrevista(Integer id, Entrevista entrevista) {

		Entrevista entrevistaAtualizada = entrevistaRepository.getReferenceById(id);
		entrevistaAtualizada.setPresenca(entrevista.getPresenca());
		entrevistaAtualizada.setSituacao(entrevista.getSituacao());

		return entrevistaRepository.save(entrevistaAtualizada);
	}

	@Transactional
	public Entrevista buscarEntrevistaPorId(Integer id) {
		Entrevista entrevista = entrevistaRepository.findById(id).get();
		return entrevista;
	}

	@Transactional
	public void excluirEntrevista(Integer id) {
		buscarEntrevistaPorId(id);
		entrevistaRepository.deleteById(id);
	}

	@Transactional
	public List<Entrevista> getEntrevistas() {
		List<Entrevista> entrevista = entrevistaRepository.findAll();
		return entrevista;
	}

	@Transactional
	public MensagemEntrevista inserirSegundaMensagem(MensagemEntrevista mensagem) {
		MensagemEntrevista msg = mensagemEntrevistaRepository.save(mensagem);
		return msg;
	}

	@Transactional
	public MensagemEntrevista atualizarSegundaMensagem(Integer id, MensagemEntrevista mensagem) {
		MensagemEntrevista msg = mensagemEntrevistaRepository.getReferenceById(id);
		msg.setTitulo(mensagem.getTitulo());
		msg.setTextoDescritivo(mensagem.getTextoDescritivo());
		msg.setLinkDaSala(mensagem.getLinkDaSala());
		mensagemEntrevistaRepository.save(msg);
		return msg;
	}

	@Transactional
	public MensagemEntrevista buscarSegundaMensagemPorId(Integer id) {
		MensagemEntrevista mensagem = mensagemEntrevistaRepository.findById(id).get();
		return mensagem;
	}

	@Transactional
	public void excluirSegundaMensagem(Integer id) {

		try {
			buscarSegundaMensagemPorId(id);
			mensagemEntrevistaRepository.deleteById(id);
		} catch (EmptyResultDataAccessException e) {
			throw new Tratamentoexcecao("Mensagem não encontrada, por favor, digite um id válido!");
		}
	}

	@Transactional
	public List<MensagemEntrevista> buscarSegundaMensagens() {
		List<MensagemEntrevista> mensagens = mensagemEntrevistaRepository.findAll();
		return mensagens;
	}

	@Transactional
	public MensagemEntrevista convidarCandidato(MensagemEntrevista convite) {
		mensagemEntrevistaRepository.save(convite);
		return convite;
	}

	@Transactional
	public MensagemEntrevista atualizarConvite(Integer id, MensagemEntrevista convite) {
		MensagemEntrevista conviteAtualizado = mensagemEntrevistaRepository.getReferenceById(id);
		conviteAtualizado.setTitulo(convite.getTitulo());
		conviteAtualizado.setTextoDescritivo(convite.getTextoDescritivo());
		conviteAtualizado.setLinkDaSala(convite.getLinkDaSala());
		conviteAtualizado.setCandidatoId(convite.getCandidatoId());
		conviteAtualizado.setDataHora(convite.getDataHora());
		mensagemEntrevistaRepository.save(conviteAtualizado);
		return conviteAtualizado;
	}

	@Transactional
	public List<MensagemEntrevista> getConvites() {
		List<MensagemEntrevista> convites = mensagemEntrevistaRepository.findAll();
		return convites;
	}

	@Transactional
	public MensagemEntrevista buscarConvitePorId(Integer id) {
		MensagemEntrevista convite = mensagemEntrevistaRepository.findById(id).get();
		return convite;
	}

	@Transactional
	public void excluirConvitePorId(Integer id) {
		buscarConvitePorId(id);
		mensagemEntrevistaRepository.deleteById(id);

	}
}
