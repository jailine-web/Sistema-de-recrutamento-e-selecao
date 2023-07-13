package com.example.app.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import com.example.app.DTO.CandidatoReduzido;
import com.example.app.controller.excecao.IdNaoEncontrado;
import com.example.app.excecao.Tratamentoexcecao;
import com.example.app.model.entities.Candidato;
import com.example.app.projection.VagasProjection;
import com.example.app.repositories.CandidatoRepository;

import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;

@Service
public class CandidatoService {

	@Autowired
	private CandidatoRepository cr;

	@Transactional
	public List<Candidato> buscarTodos() {
		List<Candidato> listaRecrutador = cr.findAll();
		return listaRecrutador;
	}

	@Transactional
	public Candidato buscarPorId(Integer id) {

		Candidato candBanco = cr.findById(id).get();
		if (id != candBanco.getId()) {
			throw new Tratamentoexcecao("Objeto não encontrado");
		}

		return candBanco;
	}

	@Transactional
	public Candidato inserirCandidato(Candidato candidato) {
		Candidato c = cr.save(candidato);
		return c;
	}

	@Transactional
	public void excluirCandidato(Integer id) {

		try {

			Candidato c = buscarPorId(id);
			Integer idC = c.getId();

			if (idC == id) {
				cr.deleteById(id);
			}
		} catch (EmptyResultDataAccessException e) {
			throw new Tratamentoexcecao("Candidato não encontrado");
		}
	}

	@Transactional
	public Candidato atualizarCandidato(Integer id, Candidato candidato) {

		try {

			Candidato candidatoAux = cr.getReferenceById(id);
			atualizarDados(candidatoAux, candidato);
			return cr.save(candidatoAux);
		}
		
		catch (EntityNotFoundException e) {
			throw new IdNaoEncontrado(id);
		}

	}

	private void atualizarDados(Candidato candidatoAux, Candidato candidato) {

		candidatoAux.setNome(candidato.getNome());
		candidatoAux.setTelefone(candidato.getTelefone());
		candidatoAux.setEmail(candidato.getEmail());
		candidatoAux.setRecrutador(candidato.isRecrutador());
		candidatoAux.setCurriculo(candidato.getCurriculo());
		candidatoAux.setImg(candidato.getImg());
		candidatoAux.setSemestreVigente(candidato.getSemestreVigente());
		candidatoAux.setCurso(candidato.getCurso());
		candidatoAux.setTermino(candidato.getTermino());
		candidatoAux.setInstituicao(candidato.getInstituicao());
	}

	public List<CandidatoReduzido> buscarCandidatos(Integer idVagas) {
		List<VagasProjection> resultado = cr.buscarCandidatosDaVaga(idVagas);
		return resultado.stream().map(x -> new CandidatoReduzido(x)).toList();
	}

}
