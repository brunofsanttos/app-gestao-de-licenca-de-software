package com.br.gestaodelicencadesoftware.repositories;

import com.br.gestaodelicencadesoftware.entities.ProofEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProofRepository extends JpaRepository<ProofEntity, Long> {}
