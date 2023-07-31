package com.example.app.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
<<<<<<< HEAD
import org.springframework.stereotype.Repository;
=======
>>>>>>> 348dd47a5a5e8e285ff0dc19e5dd0c13a5b7af82
import org.springframework.data.repository.query.Param;

import com.example.app.model.entities.Vaga;
import com.example.app.projection.CandidaturasCandidatoProjection;

import jakarta.transaction.Transactional;
<<<<<<< HEAD

@Repository
public interface VagaRepository extends JpaRepository<Vaga, Integer> {
=======
>>>>>>> 348dd47a5a5e8e285ff0dc19e5dd0c13a5b7af82

	@Transactional
	// Adicionei o Transactional para proteger o código de SQL Injection pois ela garante que todos os comandos SQL emitidos
	//pelo método sejam executados dentro de uma transação, se falhar, todos os comandos serão desfeitos protegendo contra danos
	@Query(nativeQuery = true, value = """
			SELECT  v.nome from vaga as v inner join candidato as c
			where c.id = :idCand """)
	//Parametrização de argumentos para sanitizar a entrada de dados (Anti-SQL Injection)
	List<CandidaturasCandidatoProjection> buscarCandidaturas(@Param("idCand") Integer idCand);

}
