package com.br.gestaodelicencadesoftware.entities;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDate;

@Entity(name = "license")
@Data
public class LicenseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long licenseId;

    @ManyToOne(fetch = jakarta.persistence.FetchType.LAZY)
    private SoftwareEntity softwareId;

    private String key;

    private LocalDate acquisitionDate;

    private LocalDate dueDate;

    @ManyToOne(fetch = jakarta.persistence.FetchType.LAZY)
    private RenewalEntity renewalId;

    @ManyToOne(fetch = jakarta.persistence.FetchType.LAZY)
    private ProofEntity proofId;

    @ManyToOne(fetch = jakarta.persistence.FetchType.LAZY)
    private UserEntity responseId;
}

