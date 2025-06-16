package com.br.gestaodelicencadesoftware.dtos;

import com.br.gestaodelicencadesoftware.entities.LicenseEntity;
import com.br.gestaodelicencadesoftware.entities.SupplierEntity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class SoftwareDTO {
    private Long softwareId;
    private String description;
    private String softwareVersion;
    private SupplierEntity supplierId;
    private LicenseEntity licenseId;
    private String urlSite;
    private Boolean isDeleted;
}
