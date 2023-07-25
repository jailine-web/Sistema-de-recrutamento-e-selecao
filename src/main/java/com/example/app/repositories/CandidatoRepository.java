package com.example.app.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
<<<<<<< HEAD
import org.springframework.stereotype.Repository;
=======
import org.springframework.data.repository.query.Param;
>>>>>>> 029bf59543fb7f59968d34054b683ff876cc10df

import com.example.app.model.entities.Candidato;
import com.example.app.projection.CandidaturasProjection;

<<<<<<< HEAD
@Repository
public interface CandidatoRepository extends JpaRepository<Candidato, Integer>{
=======
import jakarta.transaction.Transactional;
>>>>>>> 029bf59543fb7f59968d34054b683ff876cc10df

public interface CandidatoRepository extends JpaRepository<Candidato, Integer>{
	
	@Transactional
	@Query(nativeQuery = true, value = """
			SELECT distinct c.id, c.nome, c.telefone, c.curso, c.termino FROM CANDIDATO as c inner join VAGA as v inner join CANDIDATO_VAGA as cv
			where cv.candidato_id = c.id and cv.vaga_id = :idVagas """)
	List<CandidaturasProjection> buscarCandidatosDaVaga(@Param("idVagas") Integer idVagas);
	
	@Transactional
	default void salvarCurriculo(Integer candidatoId, byte[] curriculo) {
		Candidato candidato = findById(candidatoId).orElse(null);
		candidato.setCurriculo(curriculo);
		save(candidato);
	}
}