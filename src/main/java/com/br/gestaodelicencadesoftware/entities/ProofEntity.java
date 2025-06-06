package com.br.gestaodelicencadesoftware.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Data;

@Entity(name = "proof")
@Data
public class ProofEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long proofId;

    private String fileName;

    private String fileHash;

    private String fileExtension;

    private String fileContentType;

    private String fileUrl;
}
