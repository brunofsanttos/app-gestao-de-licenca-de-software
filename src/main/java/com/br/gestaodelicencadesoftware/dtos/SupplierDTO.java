package com.br.gestaodelicencadesoftware.dtos;

import lombok.Getter;
import lombok.Setter;

import java.io.Serial;
import java.io.Serializable;

@Getter
@Setter
public class SupplierDTO implements Serializable {
    @Serial
    private static final long serialVersionUID = 1L;

    private String companyName;
    private String cnpj;
    private Long supplierId;
    private Boolean isDeleted;

    public SupplierDTO() {
    }

    public SupplierDTO(String companyName, String cnpj) {
        this.companyName = companyName;
        this.cnpj = cnpj;
    }

    public SupplierDTO(String companyName, String cnpj, Long supplierId, Boolean isDeleted) {
        this.companyName = companyName;
        this.cnpj = cnpj;
        this.supplierId = supplierId;
        this.isDeleted = isDeleted;
    }
}
