package com.br.gestaodelicencadesoftware.entities;

import jakarta.persistence.*;
import lombok.Data;

@Entity(name = "softwares")
@Data
public class SoftwareEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long idSoftware;

    private String description;

    private String version;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "supplier_id", nullable = false)
    private SupplierEntity supplierId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "license_id", nullable = false)
    private LicenseEntity licenseId;

    private String urlSite;
}
