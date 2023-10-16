package com.example.app.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.app.model.entities.MensagemEntrevista;
import com.example.app.repositories.SegundaMensagemRepository;

import jakarta.transaction.Transactional;

@Service
public class SegundaMensagemService {
	
	@Autowired
	private SegundaMensagemRepository segundaMensagemRepository;

	@Transactional
	public MensagemEntrevista cadastrarSegundaMensagem (MensagemEntrevista segundaMensagem) {
		segundaMensagemRepository.save(segundaMensagem);
		return segundaMensagem;
	}

	@Transactional
	public MensagemEntrevista atualizarSegundaMensagem(Integer id, MensagemEntrevista segundaMensagem) {
		MensagemEntrevista MensagemAtualizada = segundaMensagemRepository.getReferenceById(id);
		MensagemAtualizada.setTitulo(segundaMensagem.getTitulo());
		MensagemAtualizada.setTextoDescritivo(segundaMensagem.getTextoDescritivo());
		MensagemAtualizada.setLinkDaSala(segundaMensagem.getLinkDaSala());
		MensagemAtualizada.setCandidatoId(segundaMensagem.getCandidatoId());
		MensagemAtualizada.setDataHora(segundaMensagem.getDataHora());
		segundaMensagemRepository.save(MensagemAtualizada);
		return MensagemAtualizada;
	}

	@Transactional
	public List<MensagemEntrevista> getMensagens() {
		List<MensagemEntrevista> convites = segundaMensagemRepository.findAll();
		return convites;
	}

	@Transactional
	public MensagemEntrevista buscarSegundaMensagemPorId(Integer id) {
		MensagemEntrevista convite = segundaMensagemRepository.findById(id).get();
		return convite;
	}

	@Transactional
	public void excluirConvitePorId(Integer id) {
		buscarSegundaMensagemPorId(id);
		segundaMensagemRepository.deleteById(id);

	}
}
