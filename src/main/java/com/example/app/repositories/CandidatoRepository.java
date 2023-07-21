package com.example.app.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.example.app.model.entities.Candidato;
import com.example.app.projection.CandidaturasProjection;

import jakarta.transaction.Transactional;

public interface CandidatoRepository extends JpaRepository<Candidato, Integer>{
	
	@Transactional
	// Adicionei o Transactional para proteger o código de SQL Injection pois ela garante que todos os comandos SQL emitidos
	//pelo método sejam executados dentro de uma transação, se falhar, todos os comandos serão desfeitos protegendo contra danos
	@Query(nativeQuery = true, value = """
			SELECT distinct c.id, c.nome, c.telefone, c.curso, c.termino FROM CANDIDATO as c inner join VAGA as v inner join CANDIDATO_VAGA as cv
			where cv.candidato_id = c.id and cv.vaga_id = :idVagas """)
	//Parametrização de argumentos para sanitizar a entrada de dados (Anti-SQL Injection)
	List<CandidaturasProjection> buscarCandidatosDaVaga(@Param("idVagas") Integer idVagas);
	
}
