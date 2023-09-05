package com.example.app.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.example.app.model.entities.EstatisticasVaga;

import jakarta.transaction.Transactional;

@Repository
public interface EstatisticasRepository extends JpaRepository<EstatisticasVaga, Integer>{
	
	@Transactional
	@Query(nativeQuery = true, value ="""
			SELECT DISTINCT count(cv.vaga_id), v.titulo vaga FROM candidaturas as cv inner join vaga as v 
			where cv.vaga_id = v.id and vaga_id = :idVaga """)
	
	Long quantidadeDeCandidaturas(Integer idVaga);
}
