package com.br.gestaodelicencadesoftware.entities;

import com.br.gestaodelicencadesoftware.enumeratedTypes.RenewalFrequency;
import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDate;

@Entity(name = "renewal")
@Data
public class RenewalEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long renewalId;

    private RenewalFrequency frequency;

    private LocalDate requestDate;

    private LocalDate responseDate;

    private Boolean isRenovateAutomatically;

    private Boolean isDeleted;

}
