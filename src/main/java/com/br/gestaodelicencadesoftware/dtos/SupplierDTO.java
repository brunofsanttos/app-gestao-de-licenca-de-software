package com.br.gestaodelicencadesoftware.dtos;

public record SupplierDTO(Long idSupplier, String companyName, String cnpj) {

    public SupplierDTO(String companyName, String cnpj) {
        this(0L, companyName, cnpj);
    }

    public SupplierDTO(Long idSupplier, String companyName, String cnpj) {
        this.idSupplier = idSupplier;
        this.companyName = companyName;
        this.cnpj = cnpj;
    }
}
