package com.example.app.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.example.app.model.entities.Vaga;
import com.example.app.projection.CandidaturasCandidatoProjection;

public interface VagaRepository extends JpaRepository<Vaga, Integer> {

	@Query(nativeQuery = true, value = """
			SELECT  v.nome from vaga as v inner join candidato as c
			where c.id = v.candidatos_id and c.id = :idCand """)

	List<CandidaturasCandidatoProjection> buscarCandidaturas(Integer idCand);

}
