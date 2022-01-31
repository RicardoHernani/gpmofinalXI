package com.ricardochaves.repositories;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ricardochaves.domain.Referencia;

@Repository
public interface ReferenciaRepository extends JpaRepository<Referencia, Integer> {
	
	Page<Referencia> findByDescricaoContainingIgnoreCase(String text, Pageable pageRequest);

}