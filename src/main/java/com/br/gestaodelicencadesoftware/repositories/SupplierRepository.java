package com.br.gestaodelicencadesoftware.repositories;

import com.br.gestaodelicencadesoftware.entities.SupplierEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SupplierRepository extends JpaRepository<SupplierEntity, Long> {
    Boolean existsByCnpj(String cnpj);
    SupplierEntity findByCnpj(String cnpj);
    SupplierEntity findByCompanyName(String companyName);
}
