package com.example.app.services;

import java.io.IOException;
import java.util.List;
import java.util.NoSuchElementException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.example.app.DTO.VagaMinDTO;
import com.example.app.controller.excecao.IdNaoEncontrado;
import com.example.app.controller.excecao.Tratamentoexcecao;
import com.example.app.model.entities.Candidato;
import com.example.app.projection.CandidaturasCandidatoProjection;
import com.example.app.repositories.CandidatoRepository;
import com.example.app.repositories.VagaRepository;

import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;

@Service
public class CandidatoService {

	@Autowired
	private CandidatoRepository cr;
	
	@Autowired
	private VagaRepository vr;

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

	public List<VagaMinDTO> buscarCandidaturas(Integer id){
		List<CandidaturasCandidatoProjection> lista = vr.buscarCandidaturas(id);
		List<VagaMinDTO> vagas = lista.stream().map(x -> new VagaMinDTO(x)).toList();
		return vagas;
	}
	
	public void inserirCurriculo(Integer candidatoId, MultipartFile curriculo) throws IOException {
		Candidato candidato = cr.findById(candidatoId).orElseThrow(() -> new NoSuchElementException("Candidato não encontrado"));
		
		byte[] curriculoBytes = curriculo.getBytes();
		candidato.setCurriculo(curriculoBytes);
		
		cr.save(candidato);
	}
}
