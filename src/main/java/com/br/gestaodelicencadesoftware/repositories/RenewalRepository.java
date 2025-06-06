package com.br.gestaodelicencadesoftware.repositories;

import com.br.gestaodelicencadesoftware.entities.RenewalEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RenewalRepository extends JpaRepository<RenewalEntity, Long> {}
