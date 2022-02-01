package com.ricardochaves.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.ricardochaves.domain.Procedimento;

@Repository
public interface ProcedimentoRepository extends JpaRepository<Procedimento, Integer>{

}
