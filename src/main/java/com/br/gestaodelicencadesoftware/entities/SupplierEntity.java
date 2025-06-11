package com.br.gestaodelicencadesoftware.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Data;

@Entity(name = "supplier")
@Data
public class SupplierEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long supplierId;

    private String companyName;

    private String cnpj;

    private Boolean isDeleted;
}
