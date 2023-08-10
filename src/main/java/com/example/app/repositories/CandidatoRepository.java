package com.example.app.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.example.app.model.entities.Candidato;
import com.example.app.projection.CandidaturasCandidatoProjection;
import com.example.app.projection.CandidaturasProjection;

import jakarta.transaction.Transactional;

@Repository
public interface CandidatoRepository extends JpaRepository<Candidato, Integer>{
	
	@Transactional
	// Adicionei o Transactional para proteger o código de SQL Injection pois ela garante que todos os comandos SQL emitidos
	//pelo método sejam executados dentro de uma transação, se falhar, todos os comandos serão desfeitos protegendo contra danos
	@Query(nativeQuery = true, value = """
			SELECT DISTINCT v.titulo FROM VAGA as v inner join CANDIDATO as c inner join CANDIDATURAS as cd
			where cd.vaga_id = v.id and cd.candidato_id = :idCand """)
	
	//Parametrização de argumentos para sanitizar a entrada de dados (Anti-SQL Injection)
	List<CandidaturasCandidatoProjection> buscarCandidaturas(@Param("idCand") Integer idCand);
	
	
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