package com.example.app.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.example.app.model.entities.Candidato;
import com.example.app.projection.CandidaturasCandidatoProjection;
import com.example.app.projection.CandidaturasProjection;
import com.example.app.projection.CurriculoAvaliadoProjection;
import com.example.app.utils.StatusCurriculoAvaliado;

import jakarta.transaction.Transactional;

@Repository
public interface CandidatoRepository extends JpaRepository<Candidato, Integer>{
	
	List<CurriculoAvaliadoProjection> findByCurriculoAvaliado(StatusCurriculoAvaliado status);
	
	@Transactional
	 @Query (nativeQuery = true, value = """
			SELECT distinct c.id, c.nome, c.telefone, c.curso, c.termino FROM CANDIDATO as c inner join VAGA as v inner join CANDIDATURAS as cd
	 		where cd.candidato_id = c.id and cd.vaga_id = :idVagas """)
	List<CandidaturasProjection> buscarCandidatosDaVaga(@Param("idVagas") Integer idVagas);
	
	
	@Transactional
	default void salvarCurriculo(Integer candidatoId, byte[] curriculo) {
		Candidato candidato = findById(candidatoId).orElse(null);
		candidato.setCurriculo(curriculo);
		save(candidato);
	}
}