package com.br.gestaodelicencadesoftware.dtos;

import com.br.gestaodelicencadesoftware.entities.ProofEntity;
import com.br.gestaodelicencadesoftware.entities.RenewalEntity;
import com.br.gestaodelicencadesoftware.entities.SoftwareEntity;
import com.br.gestaodelicencadesoftware.entities.UserEntity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class LicenseDTO {
    private Long licenseId;
    private SoftwareEntity softwareId;
    private String key;
    private LocalDate acquisitionDate;
    private LocalDate dueDate;
    private RenewalEntity renewalId;
    private ProofEntity proofId;
    private UserEntity responseId;
    private Boolean isDeleted;
}
