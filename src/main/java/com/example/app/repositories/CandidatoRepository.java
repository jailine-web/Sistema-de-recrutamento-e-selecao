package com.example.app.repositories;

import java.util.List;

import org.springframework.context.annotation.Bean;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
<<<<<<< HEAD
import org.springframework.stereotype.Repository;
=======
>>>>>>> 348dd47a5a5e8e285ff0dc19e5dd0c13a5b7af82

import com.example.app.model.entities.Candidato;
import com.example.app.projection.CandidaturasProjection;

import jakarta.transaction.Transactional;

<<<<<<< HEAD
@Repository
public interface CandidatoRepository extends JpaRepository<Candidato, Integer>{
	
=======
public interface CandidatoRepository extends JpaRepository<Candidato, Integer> {

>>>>>>> 348dd47a5a5e8e285ff0dc19e5dd0c13a5b7af82
	@Transactional
	@Query(nativeQuery = true, value = """
			SELECT distinct c.id, c.nome, c.telefone, c.curso, c.termino FROM CANDIDATO as c inner join VAGA as v inner join CANDIDATO_VAGA as cv
			where cv.candidato_id = c.id and cv.vaga_id = :idVagas """)
	List<CandidaturasProjection> buscarCandidatosDaVaga(@Param("idVagas") Integer idVagas);
<<<<<<< HEAD
	
	@Bean
=======

>>>>>>> 348dd47a5a5e8e285ff0dc19e5dd0c13a5b7af82
	@Transactional
	default void salvarCurriculo(Integer candidatoId, byte[] curriculo) {
		Candidato candidato = findById(candidatoId).orElse(null);
		candidato.setCurriculo(curriculo);
		save(candidato);
	}
}