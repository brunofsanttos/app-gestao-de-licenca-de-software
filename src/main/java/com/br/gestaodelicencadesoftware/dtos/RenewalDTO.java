package com.br.gestaodelicencadesoftware.dtos;

import com.br.gestaodelicencadesoftware.enumeratedTypes.RenewalFrequency;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class RenewalDTO {
    private Long renewalId;
    private RenewalFrequency frequency;
    private LocalDate requestDate;
    private LocalDate responseDate;
    private Boolean isRenovateAutomatically;
    private Boolean isDeleted;
}
