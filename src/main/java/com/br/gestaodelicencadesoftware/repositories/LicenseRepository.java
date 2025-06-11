package com.br.gestaodelicencadesoftware.repositories;

import com.br.gestaodelicencadesoftware.entities.LicenseEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LicenseRepository extends JpaRepository<LicenseEntity,Long> {}
