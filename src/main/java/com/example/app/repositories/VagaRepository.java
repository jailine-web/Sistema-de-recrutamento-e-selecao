package com.example.app.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
<<<<<<< HEAD
import org.springframework.stereotype.Repository;
=======
import org.springframework.data.repository.query.Param;
>>>>>>> 029bf59543fb7f59968d34054b683ff876cc10df

import com.example.app.model.entities.Vaga;
import com.example.app.projection.CandidaturasCandidatoProjection;

<<<<<<< HEAD
@Repository
public interface VagaRepository extends JpaRepository<Vaga, Integer> {
=======
import jakarta.transaction.Transactional;
>>>>>>> 029bf59543fb7f59968d34054b683ff876cc10df

public interface VagaRepository extends JpaRepository<Vaga, Integer> {
	
	@Transactional
	// Adicionei o Transactional para proteger o código de SQL Injection pois ela garante que todos os comandos SQL emitidos
	//pelo método sejam executados dentro de uma transação, se falhar, todos os comandos serão desfeitos protegendo contra danos
	@Query(nativeQuery = true, value = """
			SELECT  v.nome from vaga as v inner join candidato as c
			where c.id = :idCand """)
	//Parametrização de argumentos para sanitizar a entrada de dados (Anti-SQL Injection)
	List<CandidaturasCandidatoProjection> buscarCandidaturas(@Param("idCand") Integer idCand);

}
