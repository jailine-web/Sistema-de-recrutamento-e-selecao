package com.example.app.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.example.app.model.entities.Candidato;
import com.example.app.projection.CandidaturasProjection;

public interface CandidatoRepository extends JpaRepository<Candidato, Integer>{

	@Query(nativeQuery = true, value = """
			SELECT distinct c.id, c.nome, c.telefone, c.curso, c.termino FROM CANDIDATO as c inner join VAGA as v inner join CANDIDATO_VAGA as cv
			where cv.candidato_id = c.id and cv.vaga_id = :idVagas """)

	List<CandidaturasProjection> buscarCandidatosDaVaga(Integer idVagas);
	
	
}
