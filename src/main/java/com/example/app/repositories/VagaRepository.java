package com.example.app.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.example.app.model.entities.Vaga;
import com.example.app.projection.CandidaturasCandidatoProjection;

import jakarta.transaction.Transactional;

@Repository
public interface VagaRepository extends JpaRepository<Vaga, Integer> {

	@Transactional
	// Adicionei o Transactional para proteger o código de SQL Injection pois ela garante que todos os comandos SQL emitidos
	//pelo método sejam executados dentro de uma transação, se falhar, todos os comandos serão desfeitos protegendo contra danos
	@Query(nativeQuery = true, value = """
			SELECT DISTINCT v.titulo FROM VAGA as v inner join CANDIDATO as c inner join CANDIDATURAS as cv 
			where cv.vaga_id = v.id and cv.candidato_id = :idCand """)
	//Parametrização de argumentos para sanitizar a entrada de dados (Anti-SQL Injection)
	List<CandidaturasCandidatoProjection> buscarCandidaturas(@Param("idCand") Integer idCand);
	

}
