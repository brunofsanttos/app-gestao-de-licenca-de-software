package com.br.gestaodelicencadesoftware.dto;

public record SupplierDTO(Long idSupplier, String companyName, String cnpj) {

    public SupplierDTO(String companyName, String cnpj) {
        this(0L, companyName, cnpj);
    }
}
